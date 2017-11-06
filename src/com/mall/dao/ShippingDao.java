package com.mall.dao;

import java.util.List;

import com.mall.pojo.Shipping;

public interface ShippingDao {
	
	Integer addShipping(Shipping shipping);
	
	Integer deleteShipping(Integer shipping_id);
	
	Integer modifyShipping(Shipping shipping);
	
	List<Shipping> findShippingByUser_id(Integer user_id);
	
	//为了限制每个用户添加的地址数量,在这可以查一下
	Integer totalShippingByUser_id(Integer user_id);
	
	Shipping findShippingById(Integer shipping_id);
}
