package com.mall.dao;

import java.util.List;

import com.mall.dto.PageBean;
import com.mall.pojo.Product;;

public interface ProductDao {
	List<Product> findProduct(PageBean pageBean);
	
	Integer totalProduct(PageBean pageBean);
	
	Product findProductById(Integer product_id);
	
	List<Product> findProductByCategoryIdAndName(PageBean pageBean);
	
	Integer totalProductByCategoryIdAndName(PageBean pageBean);
	
	Integer queryProductStockById(Integer product_id);
	
	Integer modifyProductStock(Product product);

	Product findProductByIdUseOfCart(Integer product_id);
	
}
