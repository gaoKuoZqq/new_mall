package com.mall.controller;

import java.util.List;

import org.apache.log4j.lf5.PassingLogRecordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.dto.PageBean;
import com.mall.pojo.Order_item;
import com.mall.service.Order_itemService;

@Controller
@RequestMapping("orderItem")
public class OrderItemController {
	@Autowired
	Order_itemService order_itemService;
	
	@RequestMapping("see")
	@ResponseBody
	public ModelAndView see(String order_no) {
		ModelAndView modelAndView = new ModelAndView("order_item");
		List<Order_item> order_items = order_itemService.findOrder_itemByOrderNo(order_no);
		modelAndView.addObject("order_items",order_items);
		return modelAndView;
	}
}
