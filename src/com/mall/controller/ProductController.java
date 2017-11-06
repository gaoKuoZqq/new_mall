package com.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mall.dto.PageBean;
import com.mall.pojo.Product;
import com.mall.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("introduction")
	public ModelAndView introduction(Integer product_id) {
		ModelAndView modelAndView = new ModelAndView();
		Product product = productService.findProductById(product_id);
		String[] sub_images = product.getSub_images().split(",");
		modelAndView.addObject("sub_images",sub_images);
		modelAndView.addObject("product",product);
		modelAndView.setViewName("introduction");
		return modelAndView;
	}
	
	@RequestMapping("/find")
	public ModelAndView findProduct(PageBean pageBean) {
		if (pageBean.getPageIndex() == null || pageBean.getPageIndex() == 0) {
			pageBean.setPageIndex(1);
		}
		if (pageBean.getPageSize() == null || pageBean.getPageSize() == 0) {
			pageBean.setPageSize(12);
		}
		if (pageBean.getProduct() == null ) {
			pageBean.setProduct(new Product());
		}
		Integer pageIndex = pageBean.getPageIndex();
		Integer pageSize  = pageBean.getPageSize();
		pageBean.setLimitStart((pageIndex - 1) * pageSize);
		ModelAndView modelAndView = new ModelAndView();
		pageBean= productService.findProductByCategoryIdAndName(pageBean);
		modelAndView.addObject(pageBean);
		modelAndView.setViewName("product_search");
		return modelAndView;
	}
}
