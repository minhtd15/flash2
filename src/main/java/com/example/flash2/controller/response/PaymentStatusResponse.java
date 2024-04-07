package com.example.flash2.controller.response;

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
public class PaymentStatusResponse {
  private Integer paymentStatusId;
  private Integer courseManagerId;
  private float remain;
  private String paymentStatus;
  private int courseId;
  private int studentId;
  private String lastUpdatedDate;
}
