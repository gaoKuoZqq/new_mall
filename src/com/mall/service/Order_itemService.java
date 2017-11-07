package com.mall.service;

import java.util.List;

import com.mall.dto.PageBean;
import com.mall.pojo.Order_item;

public interface Order_itemService {

	Boolean addOrder_item(Order_item order_item);
	
	public PageBean findOrder_item(PageBean pageBean);

	List<Order_item> findOrder_itemByOrderNo(String order_no);
}
