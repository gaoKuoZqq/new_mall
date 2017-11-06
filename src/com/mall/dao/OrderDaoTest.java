package com.mall.dao;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mall.dto.PageBean;
import com.mall.pojo.Order;

public class OrderDaoTest {
	@Test
	public void findCategoryTest() {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext applicationContext =
		        new ClassPathXmlApplicationContext("applicationContext.xml");
		// 从容器中获取bean
		OrderDao orderDao = (OrderDao) applicationContext.getBean("orderDao");
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(3);
		pageBean.setLimitStart(0);
		Order order = new Order();
		order.setId(107);
		pageBean.setOrder(order);
		System.out.println(orderDao.findOrder(pageBean));
	}
}
