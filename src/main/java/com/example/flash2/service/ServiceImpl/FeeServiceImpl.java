package com.example.flash2.service.ServiceImpl;

import com.example.flash2.controller.request.CourseManagerRequest;
import com.example.flash2.controller.request.PaymentStatusRequest;
import com.example.flash2.controller.request.YearlyRevenueRequest;
import com.example.flash2.controller.response.PaymentStatusResponse;
import com.example.flash2.controller.response.YearlyPayment;
import com.example.flash2.dto.CourseFeeResponse;
import com.example.flash2.dto.YearlyResponse;
import com.example.flash2.entity.CourseFeeEntity;
import com.example.flash2.entity.PaymentStatusEntity;
import com.example.flash2.rabbit.RabbitMQPublisher;
import com.example.flash2.repository.CourseFeeRepository;
import com.example.flash2.repository.PaymentStatusRepository;
import com.example.flash2.service.FeeService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {

  private final PaymentStatusRepository paymentStatusRepository;
  private final CourseFeeRepository courseFeeRepository;
  private final RabbitMQPublisher rabbitMQPublisher;

  @Override
  public List<PaymentStatusResponse> getPaymentStatus(PaymentStatusRequest request) {
    List<Integer> courseManagerIds = new ArrayList<>();
    if (request != null && request.getCourseManagerRequest() != null) {
      for (CourseManagerRequest managerRequest : request.getCourseManagerRequest()) {
        courseManagerIds.add(managerRequest.getCourseManagerId());
      }
    } else {
      throw new IllegalArgumentException("Request is null");
    }

    Map<Integer, Integer> map = new HashMap<>();
    for (CourseManagerRequest temp : request.getCourseManagerRequest()) {
      map.put(temp.getCourseManagerId(), temp.getStudentId());
    }


    List<PaymentStatusEntity> paymentStatusEntities = paymentStatusRepository.getPaymentStatusEntitiesByCourseId(
        courseManagerIds);
    List<PaymentStatusResponse> response = paymentStatusEntities.stream()
        .map(paymentStatusEntity -> PaymentStatusResponse.builder()
            .paymentStatusId(paymentStatusEntity.getPaymentStatusId())
            .courseManagerId(paymentStatusEntity.getCourseManagerId())
            .studentId(map.get(paymentStatusEntity.getCourseManagerId()))
            .remain(paymentStatusEntity.getRemain())
            .paymentStatus(paymentStatusEntity.getStatus())
            .lastUpdatedDate(paymentStatusEntity.getLastUpdatedDate())
            .build())
        .collect(Collectors.toList());

    return response;
  }

  @Override
  public YearlyPayment getYearlyRevenue(List<YearlyRevenueRequest> request, String year) throws Exception {
    List<Integer> courseTypeIds = request.stream()
        .map(YearlyRevenueRequest::getCourseTypeId)
        .collect(Collectors.toList());

    // Sử dụng danh sách courseId để lấy thông tin CourseFeeEntity
    List<CourseFeeEntity> courseFeeEntities = courseFeeRepository.getByCourseTypeId(courseTypeIds);

    Map<Integer, Double> courseFeeMap = new HashMap<>();
    for (CourseFeeEntity courseFeeEntity : courseFeeEntities) {
      courseFeeMap.put(courseFeeEntity.getCourseTypeId(), courseFeeEntity.getFeePerStudent());
    }

    List<CourseFeeResponse> yearlyResponses = request.stream()
        .map(rq -> CourseFeeResponse.builder()
            .courseId(rq.getCourseId())
            .revenue(rq.getTotalStudent() * courseFeeMap.get(
                rq.getCourseTypeId()))
            .build()).toList();

    double totalRevenue = yearlyResponses.stream()
        .mapToDouble(CourseFeeResponse::getRevenue)
        .sum();

    YearlyResponse yearlyResponse = YearlyResponse.builder()
        .totalYearlyRevenue(totalRevenue)
        .courseFeeResponses(yearlyResponses)
        .year(year)
        .build();

    // push to rabbitmq
    rabbitMQPublisher.sendMessage(yearlyResponse, "queue");

    return YearlyPayment.builder()
        .message("success")
        .build();
  }
}
