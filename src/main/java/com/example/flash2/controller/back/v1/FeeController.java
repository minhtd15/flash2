package com.example.flash2.controller.back.v1;

import com.example.flash2.controller.request.FeeRequest;
import com.example.flash2.controller.request.PaymentStatusRequest;
import com.example.flash2.controller.request.YearlyRevenueRequest;
import com.example.flash2.controller.response.FeeResponse;
import com.example.flash2.controller.response.PaymentStatusResponse;
import com.example.flash2.controller.response.YearlyPayment;
import com.example.flash2.rabbit.RabbitMQPublisher;
import com.example.flash2.service.CourseFeeService;
//import com.example.flash.service.FeeService;
import com.example.flash2.service.FeeService;
import io.swagger.v3.oas.annotations.info.Info;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
@RequestMapping(path = {"b/v1/flash"})
public class FeeController {
  private final FeeService feeService;
  private final CourseFeeService courseFeeService;
  private final RabbitMQPublisher rabbitMQPublisher;

  @GetMapping(value = "/goodbye", produces = "application/json")
  public ResponseEntity<Double> getCourseRevenue( @RequestParam(value = "param", required = true, defaultValue = "") String param) {
    Double response = courseFeeService.getCourseFeeByCourseId(param);
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = "/paymentStatus", produces = "application/json")
  public ResponseEntity<List<PaymentStatusResponse>> getPaymentStatus(@RequestBody PaymentStatusRequest request) {
    log.info("Payment status requested");
    List<PaymentStatusResponse> response = feeService.getPaymentStatus(request);
    //rabbitMQPublisher.sendMessage("Payment status requested");
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = "/yearly-revenue", produces = "application/json")
  public ResponseEntity<YearlyPayment> getYearlyRevenue(@RequestBody List<YearlyRevenueRequest> request, @RequestParam(value = "year", required = true, defaultValue = "") String year) {
    try {
      log.info("Year param is: {}", year);
      YearlyPayment response = feeService.getYearlyRevenue(request, year);
      log.info("Yearly revenue requested");
      return ResponseEntity.ok(response); // Trả về 200 (OK) và dữ liệu được trả về
    } catch (Exception e) {
      // Xử lý lỗi và trả về mã trạng thái 500 (Internal Server Error)
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
