package com.mall.dao;

import java.util.List;

import com.mall.dto.PageBean;
import com.mall.pojo.Order;
import com.mall.pojo.Order_item;

public interface Order_itemDao {

	Integer addOrder_item(Order_item order_item);
	
	List<Order> findOrder_item(PageBean pageBean);
	
	Integer totalOrder_item(PageBean pageBean);
}
