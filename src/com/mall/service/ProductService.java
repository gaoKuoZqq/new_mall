package com.mall.service;

import com.mall.dto.PageBean;
import com.mall.pojo.Product;

public interface ProductService {
	
	public Product findProductByIdUseOfCart(Integer product_id);
	
	public PageBean findProductByCategoryIdAndName(PageBean pageBean);
	
	Boolean modifyProductStock(Product product);
	
	Integer queryProductStockById(Integer product_id);
	
	Product findProductById(Integer product_id);
}
