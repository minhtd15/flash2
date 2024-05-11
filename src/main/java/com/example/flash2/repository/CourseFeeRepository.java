package com.example.flash2.repository;

import com.example.flash2.entity.CourseFeeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseFeeRepository extends JpaRepository<CourseFeeEntity, Integer> {
  @Query(nativeQuery = true, value = "SELECT c.* FROM COURSE_FEE c WHERE c.COURSE_TYPE_ID IN (:courseTypeId)")
  List<CourseFeeEntity> getByCourseTypeId(List<Integer> courseTypeId);
}
