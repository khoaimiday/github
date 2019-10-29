package com.myclass.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.entity.Courses;
import com.myclass.repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	CourseRepository courseRepository;
	
	public List<Courses> findAllCourses(){
		return courseRepository.findAll();
	}
	
	public boolean insert(Courses entity) {
		try {
			courseRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Optional<Courses> findById(int id){
		return courseRepository.findById(id);
	}
	
	public boolean update(Courses entity) {
		try {
			courseRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void delete(int id) {
		courseRepository.deleteById(id);
	}
}
