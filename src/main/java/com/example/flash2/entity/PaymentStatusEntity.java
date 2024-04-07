package com.example.flash2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PAYMENT_STATUS")
public class PaymentStatusEntity {
  @jakarta.persistence.Id
  @Column(name = "PAYMENT_STATUS_ID")
  private Integer paymentStatusId;

  @Column(name="COURSE_MANAGER_ID")
  private Integer courseManagerId;

  @Column(name="REMAIN")
  private float remain;

  @Column(name="STATUS")
  private String status;

  @Column(name="LAST_UPDATED_DATE")
  private String lastUpdatedDate;
}
