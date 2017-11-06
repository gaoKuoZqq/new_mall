package com.mall.service;

import java.util.List;

import com.mall.pojo.Category;

public interface CategoryService {
	
	public List<Category> findRootCategory();
	
	public List<Category> findCategoryByParent_id (Integer parent_id);
	
}
