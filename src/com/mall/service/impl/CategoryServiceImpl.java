package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.CategoryDao;
import com.mall.pojo.Category;
import com.mall.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDao categoryDao;
	@Override
	public List<Category> findRootCategory() {
		return categoryDao.findRootCategory();
	}
	
	@Override
	public List<Category> findCategoryByParent_id(Integer parent_id) {
		return categoryDao.findCategoryByParent_id(parent_id);
	}
}
