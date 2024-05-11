package com.example.flash2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder.In;
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
@Table(name = "COURSE_FEE")
public class CourseFeeEntity {
  @Column(name = "COURSE_FEE_ID")
  private Integer Id;

  @jakarta.persistence.Id
  @Column(name = "COURSE_TYPE_ID")
  private Integer CourseTypeId;

  @Column(name = "FEE_PER_STUDENT")
  private double FeePerStudent;
}

