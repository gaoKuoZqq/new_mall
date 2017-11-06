package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.CartDao;
import com.mall.pojo.Cart;
import com.mall.service.CartService;
@Service
public class CartServiceImpl implements CartService{
	@Autowired
	CartDao cartDao;
	
	public Boolean deleteCartsByIdsList(String[] cart_ids){
		return cartDao.deleteCarts(cart_ids) > 0;
	}
	
	public List<Cart> findCartsByIdsList(String[] cart_ids){
		return cartDao.findCartsByIdsList(cart_ids);
	}
	
	public Boolean modifyCartQuantity(Cart cart) {
		return cartDao.modifyCartQuantity(cart) > 0;
	}

	@Override
	public Boolean addCart(Cart cart) {
		return cartDao.addCart(cart) > 0;
	}

	@Override
	public List<Cart> findCart(Integer user_id) {
		return cartDao.findCartByuser_id(user_id);
	}

	@Override
	public void modifyCartCheckedFalse(Integer user_id) {
		cartDao.modifyCartCheckedFalse(user_id);
	}

	@Override
	public void modifyCartCheckedTrue(Cart cart) {
		cartDao.modifyCartCheckedTrue(cart);
	}

	@Override
	public Cart findCartByNewCart(Cart cart) {
		return cartDao.findCartByNewCart(cart);
	}
	
	
}
