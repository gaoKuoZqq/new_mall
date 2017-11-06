package com.mall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.ProductDao;
import com.mall.dto.PageBean;
import com.mall.pojo.Product;
import com.mall.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDao productDao;
	
	public Product findProductByIdUseOfCart(Integer product_id){
		return productDao.findProductByIdUseOfCart(product_id);
	}
	
	public Integer queryProductStockById(Integer product_id) {
		return productDao.queryProductStockById(product_id);
	}
	
	public Product findProductById(Integer product_id) {
		return productDao.findProductById(product_id);
	}

	@Override
	public PageBean findProductByCategoryIdAndName(PageBean pageBean) {
		Integer totalProduct = productDao.totalProductByCategoryIdAndName(pageBean);
		Integer totalPage = (int) Math.ceil(1.0*totalProduct/pageBean.getPageSize());
		//避免出现页码超范围
		if (totalPage < pageBean.getPageIndex()) {
			pageBean.setPageIndex(totalPage);
			if (totalPage > 20) {
				totalPage = 20;
				pageBean.setPageIndex(totalPage);
			}
			pageBean.setLimitStart((totalPage - 1) * pageBean.getPageSize() >= 0 ? (totalPage - 1) * pageBean.getPageSize() : 0);
		}
		List<Product> productsList = productDao.findProductByCategoryIdAndName(pageBean);
		pageBean.setObjList(productsList);
		pageBean.setTotalObj(totalProduct);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public Boolean modifyProductStock(Product product) {
		return productDao.modifyProductStock(product) > 0;
	}

}
