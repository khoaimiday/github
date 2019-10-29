package com.myclass.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.entity.Category;
import com.myclass.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	public boolean insert(Category category) {
		try {
			categoryRepository.save(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Optional<Category> findById(int id) {
		return categoryRepository.findById(id);
	}

	public boolean update(Category category) {
		try {
			categoryRepository.save(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void delete(int id) {
		categoryRepository.deleteById(id);
	}
}
