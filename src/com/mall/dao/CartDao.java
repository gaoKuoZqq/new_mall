package com.mall.dao;

import java.util.List;

import com.mall.pojo.Cart;

public interface CartDao {
	
	Integer addCart(Cart cart);
	
	Integer deleteCarts(String[] ids);
	
	Integer modifyCartQuantity(Cart cart);
	
	List<Cart> findCartByuser_id(Integer user_id);
	
	//注销时将勾选状态变为未勾选
	void modifyCartCheckedFalse(Integer user_id);
	//将cart状态改为已勾选
	void modifyCartCheckedTrue(Cart cart);
	
	//为了把同样的商品归为一条,先查一下有没有
	Cart findCartByNewCart(Cart cart);
	
	List<Cart> findCartsByIdsList(String[] ids);
	
	List<Cart> findCartQuantityAndProductPriceByIdsList(String[] ids);
}
