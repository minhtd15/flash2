package com.example.flash2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "COURSE")
public class CourseEntity {
  @Id
  @Column(name = "COURSE_ID")
  private Integer Id;

  @Column(name = "COURSE_TYPE_ID")
  private Integer courseTypeId;

  @Column(name = "MAIN_TEACHER")
  private String mainTeacher;
}
