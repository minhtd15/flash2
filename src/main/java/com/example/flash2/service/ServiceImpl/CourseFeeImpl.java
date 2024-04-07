package com.example.flash2.service.ServiceImpl;

import com.example.flash2.controller.request.PaymentStatusRequest;
import com.example.flash2.controller.response.PaymentStatusResponse;
import com.example.flash2.service.CourseFeeService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CourseFeeImpl implements CourseFeeService {

  @Override
  public double getCourseFeeByCourseId(String courseId) {
    return 0;
  }
}
