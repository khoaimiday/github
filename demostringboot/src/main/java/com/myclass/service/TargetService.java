package com.myclass.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.entity.Target;
import com.myclass.repository.TargetRepository;

@Service
public class TargetService {

	@Autowired
	TargetRepository targetRepository;
	
	public List<Target> targets(){
		return targetRepository.findAll();
	}
	
	public Optional<Target> findById(int id) {
		return targetRepository.findById(id);
	}
	
	public boolean insert(Target entity) {
		try {
			targetRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(Target entity) {
		try {
			targetRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void delete(int id) {
		targetRepository.deleteById(id);
	}
}
