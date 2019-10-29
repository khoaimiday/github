package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Courses;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Integer> {

}