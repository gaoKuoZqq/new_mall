package com.mall.dao;

import java.util.List;

import com.mall.pojo.Category;

public interface CategoryDao {
	
	List<Category> findRootCategory();
	
	List<Category> findCategoryByParent_id(Integer parent_id);
	
}