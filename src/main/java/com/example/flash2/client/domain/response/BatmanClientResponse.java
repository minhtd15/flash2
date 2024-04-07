package com.example.flash2.client.domain.response;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatmanClientResponse {
  private int courseId;
  private String courseName;
  private String mainTeacher;
  private String room;
  private int totalSessions;
}
