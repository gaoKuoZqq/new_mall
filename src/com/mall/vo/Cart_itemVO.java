package com.mall.vo;

import com.mall.pojo.Product;

public class Cart_itemVO {
	private Product product;
	private Integer quantity;
	public Cart_itemVO(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	public Cart_itemVO() {
		super();
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Cart_itemVO [product=" + product + ", quantity=" + quantity + "]";
	}
	
}
