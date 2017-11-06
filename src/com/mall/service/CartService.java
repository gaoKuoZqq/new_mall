package com.mall.service;

import java.util.List;

import com.mall.pojo.Cart;

public interface CartService {
	
	Boolean addCart(Cart cart);
	
	Boolean deleteCartsByIdsList(String[] cart_ids);
	
	Boolean modifyCartQuantity(Cart cart);
	
	List<Cart> findCart(Integer user_id);
	
	//注销时将勾选状态变为未勾选,未使用的方法
	void modifyCartCheckedFalse(Integer user_id);
	
	void modifyCartCheckedTrue(Cart cart);
	
	//List<Cart> findCartByCartIdSet(Set<Integer> cart_idsSet);
	
	//为了把同样的商品归为一条,先查一下有没有
	Cart findCartByNewCart(Cart cart);
	
	List<Cart> findCartsByIdsList(String[] cart_ids);
}
