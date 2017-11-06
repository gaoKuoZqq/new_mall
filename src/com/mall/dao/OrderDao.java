package com.mall.dao;

import java.util.List;

import com.mall.dto.PageBean;
import com.mall.pojo.Order;

public interface OrderDao {

	Integer addOrder(Order order);

	List<Order> findOrder(PageBean pageBean);
	
	Integer totalOrder(PageBean pageBean);
	
	List<Order> findOrderByUserId(Integer user_id);
}
