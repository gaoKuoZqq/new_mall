package com.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.pojo.Carousel;
import com.mall.pojo.Category;
import com.mall.service.CarouselService;
import com.mall.service.CategoryService;
import com.mall.service.ProductService;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	CarouselService carouselService;
	
	@RequestMapping("gohome")
	public ModelAndView goHome() {
		ModelAndView modelAndView = new ModelAndView();
		List<Category> rootCategoriesList = categoryService.findRootCategory();
		List<Carousel> carouselsList = carouselService.findCarousel();
		modelAndView.addObject("carouselsList",carouselsList);
		modelAndView.addObject("rootCategoriesList",rootCategoriesList);
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@RequestMapping("findcategory")
	@ResponseBody
	public List<Category> findCategoryByParent_id(Integer parent_id) {
		List<Category> categoriesList = categoryService.findCategoryByParent_id(parent_id);
		return categoriesList;
	}
	
}
