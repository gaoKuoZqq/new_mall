package com.mall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.dao.Order_itemDao;
import com.mall.dto.PageBean;
import com.mall.pojo.Order;
import com.mall.pojo.Order_item;
import com.mall.service.Order_itemService;
@Service("order_itemService")
public class Order_itemServiceImpl implements Order_itemService{
	@Resource(name="order_itemDao")
	Order_itemDao order_itemDao;
	@Override
	public Boolean addOrder_item(Order_item order_item) {
		return order_itemDao.addOrder_item(order_item) > 0;
	}
	
	public PageBean findOrder_item(PageBean pageBean) {
		Integer totalOrder_item = order_itemDao.totalOrder_item(pageBean);
		Integer totalPage = (int) Math.ceil(1.0*totalOrder_item/pageBean.getPageSize());
		//避免出现页码超范围
		if (totalPage < pageBean.getPageIndex()) {
			pageBean.setPageIndex(totalPage);
			pageBean.setLimitStart((totalPage - 1) * pageBean.getPageSize() >= 0 ? (totalPage - 1) * pageBean.getPageSize() : 0);
		}
		List<Order> ordersList = order_itemDao.findOrder_item(pageBean);
		pageBean.setObjList(ordersList);
		pageBean.setTotalObj(totalOrder_item);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

}
