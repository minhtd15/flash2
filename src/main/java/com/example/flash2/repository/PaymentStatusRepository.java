package com.example.flash2.repository;

import com.example.flash2.entity.CourseEntity;
import com.example.flash2.entity.PaymentStatusEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatusEntity, Integer> {
  @Query(nativeQuery = true, value =  "SELECT p.* FROM PAYMENT_STATUS p WHERE p.COURSE_MANAGER_ID IN :courseManagerIds")
  List<PaymentStatusEntity> getPaymentStatusEntitiesByCourseId(@Param("courseManagerIds") List<Integer> courseManagerIds);
}
