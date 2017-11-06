package com.mall.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartVO {
	private List<Cart_itemVO> cart_itemVOsList = new ArrayList<Cart_itemVO>();
	private Integer product_id;
	
	public void addCart_itemVO(Cart_itemVO cart_item) {
		boolean isExist = false;
		for (Cart_itemVO cart_itemVO : cart_itemVOsList) {
			if (cart_itemVO.getProduct().getId() == cart_item.getProduct().getId()) {
				isExist = true;
				cart_itemVO.setQuantity(cart_item.getQuantity() + cart_itemVO.getQuantity());
				if (cart_itemVO.getQuantity() > cart_itemVO.getProduct().getStock()) {
					cart_itemVO.setQuantity(cart_itemVO.getProduct().getStock());
				}
				break;
			}
		}
		if (!isExist) {
			cart_itemVOsList.add(cart_item);
		}
	}
	@JsonIgnore
	public Double getTotalPrice() {
		Double totalPrice = 0.0;
		for (Cart_itemVO cart_itemVO : cart_itemVOsList) {
			totalPrice = totalPrice + cart_itemVO.getQuantity() * cart_itemVO.getProduct().getPrice();
		}
		return totalPrice;
	}

	public String toString() {
		return "CartVO [cart_itemVOsList=" + cart_itemVOsList + ", product_id=" + product_id + "]";
	}

	public List<Cart_itemVO> getCart_itemVOsList() {
		return cart_itemVOsList;
	}

	public void setCart_itemVOsList(List<Cart_itemVO> cart_itemVOsList) {
		this.cart_itemVOsList = cart_itemVOsList;
	}
	
	@JsonIgnore
	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
}
