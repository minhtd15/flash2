package com.example.flash2.service.ServiceImpl;

import com.example.flash2.controller.request.CourseManagerRequest;
import com.example.flash2.controller.request.PaymentStatusRequest;
import com.example.flash2.controller.response.PaymentStatusResponse;
import com.example.flash2.entity.PaymentStatusEntity;
import com.example.flash2.repository.PaymentStatusRepository;
import com.example.flash2.service.FeeService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {
    private final PaymentStatusRepository paymentStatusRepository;

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

      List<PaymentStatusEntity> paymentStatusEntities = paymentStatusRepository.getPaymentStatusEntitiesByCourseId(courseManagerIds);
      List<PaymentStatusResponse> response = paymentStatusEntities.stream()
          .map(paymentStatusEntity -> PaymentStatusResponse.builder()
              .paymentStatusId(paymentStatusEntity.getPaymentStatusId())
              .courseManagerId(paymentStatusEntity.getCourseManagerId())
              .remain(paymentStatusEntity.getRemain())
              .paymentStatus(paymentStatusEntity.getStatus())
              .lastUpdatedDate(paymentStatusEntity.getLastUpdatedDate())
              .build())
          .collect(Collectors.toList());

      return response;
    }
}
