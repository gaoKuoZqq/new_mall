package com.mall.service;

import com.mall.dto.PageBean;
import com.mall.pojo.Order_item;

public interface Order_itemService {

	Boolean addOrder_item(Order_item order_item);
	
	public PageBean findOrder_item(PageBean pageBean);
}
