package com.mall.service;

import java.util.List;

import com.mall.pojo.Shipping;

public interface ShippingService {
	
	Boolean addShipping(Shipping shipping);
	
	Boolean deleteShipping(Integer shipping_id);
	
	Boolean modifyShipping(Shipping shipping);
	
	List<Shipping> findShippingByUser_id(Integer user_id);
	
	Shipping findShippingById(Integer shipping_id);
}
