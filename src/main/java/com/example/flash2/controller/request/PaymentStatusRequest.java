package com.example.flash2.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusRequest {
  private int courseTypeId;
  private CourseManagerRequest[] courseManagerRequest;
}
