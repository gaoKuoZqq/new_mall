package com.mall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.pojo.Location;
import com.mall.pojo.Shipping;
import com.mall.pojo.User;
import com.mall.service.LocationService;
import com.mall.service.ShippingService;
import com.mall.service.UserService;

@Controller
@RequestMapping("/shipping")
public class ShippingController {
	@Autowired
	ShippingService shippingService;
	@Autowired
	UserService userService;
	@Autowired
	LocationService locationService;
	
	@RequestMapping("goadd")
	@ResponseBody
	public ModelAndView goAdd(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("shipping");
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if (user == null) {
			modelAndView.setViewName("redirect:/home/gohome.shtml");
			return modelAndView;
		}
		List<Location> provinces = locationService.findProvince();
		modelAndView.addObject("provinces", provinces);
		return modelAndView;
	}
	@RequestMapping("add")
	@ResponseBody
	public Boolean addShipping(Shipping shipping,HttpServletRequest request){
		if (shipping == null || 
				shipping.getReceiver_name() == null || shipping.getReceiver_name().trim().equals("") || 
				shipping.getReceiver_mobile() == null || shipping.getReceiver_mobile().trim().equals("") || 
				shipping.getReceiver_address() == null || shipping.getReceiver_address().trim().equals("")) {
			return false;
		}
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return false;
		}
		shipping.setUser_id(user.getId());
		return shippingService.addShipping(shipping);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Boolean deleteShipping(Integer shipping_id){
		return shippingService.deleteShipping(shipping_id);
	}
	
	@RequestMapping("modify")
	@ResponseBody
	public Boolean modifyShipping(Shipping shipping){
		if (shipping == null || 
				shipping.getReceiver_name() == null || shipping.getReceiver_name().trim().equals("") || 
				shipping.getReceiver_mobile() == null || shipping.getReceiver_mobile().trim().equals("") || 
				shipping.getReceiver_address() == null || shipping.getReceiver_address().trim().equals("")) {
			return false;
		}
		return shippingService.modifyShipping(shipping);
	}
	
}
