package com.mall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.dao.ShippingDao;
import com.mall.pojo.Shipping;
import com.mall.service.ShippingService;
@Service("shippingService")
public class ShippingServiceImpl implements ShippingService{
	@Resource(name="shippingDao")
	ShippingDao shippingDao;
	
	@Override
	public Boolean addShipping(Shipping shipping) {
		Integer totalShipping  = shippingDao.totalShippingByUser_id(shipping.getUser_id());
		if (totalShipping >= 3) {
			return false;
		}
		return shippingDao.addShipping(shipping) > 0;
	}
	
	@Override
	public Boolean modifyShipping(Shipping shipping) {
		return shippingDao.modifyShipping(shipping) > 0;
	}

	@Override
	public Boolean deleteShipping(Integer shipping_id) {
		return shippingDao.deleteShipping(shipping_id) > 0;
	}

	@Override
	public List<Shipping> findShippingByUser_id(Integer user_id) {
		return shippingDao.findShippingByUser_id(user_id);
	}

	@Override
	public Shipping findShippingById(Integer shipping_id) {
		return shippingDao.findShippingById(shipping_id);
	}

}
