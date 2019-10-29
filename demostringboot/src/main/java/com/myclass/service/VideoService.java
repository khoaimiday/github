package com.myclass.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.entity.Video;
import com.myclass.repository.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	VideoRepository videoRepository;
	
	public List<Video> videos(){
		return videoRepository.findAll();
	}
	
	public Optional<Video> findById(int id) {
		return videoRepository.findById(id);
	}
	
	public boolean insert(Video entity) {
		try {
			videoRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(Video entity) {
		try {
			videoRepository.save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void delete(int id) {
		videoRepository.deleteById(id);
	}
}
