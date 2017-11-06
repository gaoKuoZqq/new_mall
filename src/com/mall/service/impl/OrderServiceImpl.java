package com.mall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.CartDao;
import com.mall.dao.OrderDao;
import com.mall.dto.PageBean;
import com.mall.pojo.Cart;
import com.mall.pojo.Order;
@Service("orderService")
public class OrderServiceImpl implements com.mall.service.OrderService{
	@Autowired
	OrderDao orderDao;
	@Autowired
	CartDao cartDao;
	@Override
	public Boolean addOrder(Order order) {
		return orderDao.addOrder(order) > 0;
	}

	public PageBean findOrder(PageBean pageBean) {
		Integer totalOrder = orderDao.totalOrder(pageBean);
		Integer totalPage = (int) Math.ceil(1.0*totalOrder/pageBean.getPageSize());
		//避免出现页码超范围
		if (totalPage < pageBean.getPageIndex()) {
			pageBean.setPageIndex(totalPage);
			pageBean.setLimitStart((totalPage - 1) * pageBean.getPageSize() >= 0 ? (totalPage - 1) * pageBean.getPageSize() : 0);
		}
		List<Order> ordersList = orderDao.findOrder(pageBean);
		pageBean.setObjList(ordersList);
		pageBean.setTotalObj(totalOrder);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public double queryPaymentByCart_idsList(String[] cart_ids) {
		List<Cart> cartsList = cartDao.findCartQuantityAndProductPriceByIdsList(cart_ids);
		double payment = 0;
		for (Cart cart : cartsList) {
			payment = payment + cart.getQuantity() * cart.getProduct().getPrice();
		}
		return payment;
	}

	@Override
	public List<Order> findOrderByUserId(Integer id) {
		return orderDao.findOrderByUserId(id);
	}
}
