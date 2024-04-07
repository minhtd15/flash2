package com.example.flash2.service;

import com.example.flash2.controller.request.PaymentStatusRequest;
import com.example.flash2.controller.response.PaymentStatusResponse;
import java.util.List;

public interface FeeService {
  List<PaymentStatusResponse> getPaymentStatus(PaymentStatusRequest request);
}

