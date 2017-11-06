package com.mall.dto;

import java.util.List;

import com.mall.pojo.Category;
import com.mall.pojo.Order;
import com.mall.pojo.Order_item;
import com.mall.pojo.Product;

public class PageBean {
	Integer pageIndex;
	Integer limitStart;
	Integer pageSize;
	Integer totalObj;
	Integer totalPage;
	List<?> objList;
	Category category;
	Product product;
	Order order;
	Order_item order_item;
	public Order_item getOrder_item() {
		return order_item;
	}
	public void setOrder_item(Order_item order_item) {
		this.order_item = order_item;
	}
	public PageBean(Integer pageIndex, Integer limitStart, Integer pageSize, Integer totalObj, Integer totalPage,
			List<?> objList, Category category, Product product, Order order) {
		super();
		this.pageIndex = pageIndex;
		this.limitStart = limitStart;
		this.pageSize = pageSize;
		this.totalObj = totalObj;
		this.totalPage = totalPage;
		this.objList = objList;
		this.category = category;
		this.product = product;
		this.order = order;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public PageBean(Integer pageIndex, Integer limitStart, Integer pageSize, Integer totalObj, Integer totalPage,
			List<?> objList, Category category, Product product) {
		super();
		this.pageIndex = pageIndex;
		this.limitStart = limitStart;
		this.pageSize = pageSize;
		this.totalObj = totalObj;
		this.totalPage = totalPage;
		this.objList = objList;
		this.category = category;
		this.product = product;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public PageBean(Integer pageIndex, Integer limitStart, Integer pageSize, Integer totalObj, Integer totalPage,
			List<?> objList, Category category) {
		super();
		this.pageIndex = pageIndex;
		this.limitStart = limitStart;
		this.pageSize = pageSize;
		this.totalObj = totalObj;
		this.totalPage = totalPage;
		this.objList = objList;
		this.category = category;
	}
	public PageBean(Integer pageIndex, Integer limitStart, Integer pageSize, Integer totalObj, Integer totalPage,
			List<?> objList) {
		super();
		this.pageIndex = pageIndex;
		this.limitStart = limitStart;
		this.pageSize = pageSize;
		this.totalObj = totalObj;
		this.totalPage = totalPage;
		this.objList = objList;
	}
	public PageBean() {
		super();
	}
	public PageBean(Integer pageIndex, Integer pageSize) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(Integer limitStart) {
		this.limitStart = limitStart;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalObj() {
		return totalObj;
	}
	public void setTotalObj(Integer totalObj) {
		this.totalObj = totalObj;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<?> getObjList() {
		return objList;
	}
	public void setObjList(List<?> objList) {
		this.objList = objList;
	}
	@Override
	public String toString() {
		return "PageBean [pageIndex=" + pageIndex + ", limitStart=" + limitStart + ", pageSize=" + pageSize
				+ ", totalObj=" + totalObj + ", totalPage=" + totalPage + ", objList=" + objList + ", category="
				+ category + ", product=" + product + ", order=" + order + ", order_item=" + order_item + "]";
	}
	
}
