package com.mall.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.pojo.Cart;
import com.mall.pojo.Location;
import com.mall.pojo.Order;
import com.mall.pojo.Order_item;
import com.mall.pojo.Product;
import com.mall.pojo.Shipping;
import com.mall.pojo.User;
import com.mall.service.CartService;
import com.mall.service.LocationService;
import com.mall.service.OrderService;
import com.mall.service.Order_itemService;
import com.mall.service.ProductService;
import com.mall.service.ShippingService;
import com.mall.service.UserService;
import com.mall.vo.CartVO;
import com.mall.vo.Cart_itemVO;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	CartService cartService;
	@Autowired
	LocationService locationService;
	@Autowired
	UserService userService;
	@Autowired
	ShippingService shippingService;
	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;
	@Autowired
	Order_itemService order_itemService;
	
	@RequestMapping("goadd")
	public ModelAndView goAddOrder(String cart_ids,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession(true);
		if (session.getAttribute("user") == null || cart_ids == null || cart_ids.trim().equals("")) {
			modelAndView.setViewName("redirect:/home/gohome.shtml");
			return modelAndView;
		}
		User user = (User) session.getAttribute("user");
		List<Shipping> shippingsList = shippingService.findShippingByUser_id(user.getId());
		modelAndView.addObject("shippingsList",shippingsList);
		
		String[] idsList = cart_ids.trim().split(" ");
		List<Cart> cartsList = cartService.findCartsByIdsList(idsList);
		Double payment = 0.0;
		for (Cart cart : cartsList) {
			payment = payment + cart.getProduct().getPrice() * cart.getQuantity();
		}
		modelAndView.addObject("payment",payment);
		modelAndView.addObject("cartsList",cartsList);
		//在这里添加省份表,用以添加收货地址,也可以在点击添加时ajax,但是数据量较小,直接传过去了
		List<Location> provinceslist = locationService.findProvince();
		modelAndView.addObject("provinceslist",provinceslist);
		
		//把cart_ids发过去,下面的add还要用
		modelAndView.addObject("cart_ids",cart_ids);
		
		modelAndView.setViewName("order");
		return modelAndView;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ModelAndView addOrder(String cart_ids,Integer shipping_id,HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		//检验登录状态
		if (session.getAttribute("user") == null || cart_ids == null || cart_ids.trim().equals("") ) {
			modelAndView.setViewName("redirect:/home/gohome.shtml");
			return modelAndView;
		}
		//先搞一个order
		Order order = new Order();
		//得到用户
		User user = (User) session.getAttribute("user");
		//生成订单号
		String order_no = UUID.randomUUID().toString().replace("-", "");
		
		//获得所选商品id,得到cart列表,根据数量计算总价
		String[] idsList = cart_ids.trim().split(" ");
		List<Cart> cartsList = cartService.findCartsByIdsList(idsList);
		Double payment = 0.0;
		//生成order_item
		Order_item order_item = new Order_item();
		//-------------------------------------找出cart_cookie
		Cookie[] cookies = request.getCookies();
		List<Cart_itemVO> cart_itemVOsList = new ArrayList<Cart_itemVO>();
		CartVO cartVO = new CartVO();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("cart_cookie".equals(cookie.getName())) {
					ObjectMapper objectMapper = new ObjectMapper();
					//只有对象里面不是null的才转换
					objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
					String value = cookie.getValue();
					try {
						cartVO = objectMapper.readValue(value, CartVO.class);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (cartVO != null) {
						cart_itemVOsList = cartVO.getCart_itemVOsList();
					}
					break;
				}
			}
		}
		//-------------------------------------------
		//用于更改产品库存的product
		Product product = new Product();
		for (Cart cart : cartsList) {
			if (cart == null) {
				modelAndView.setViewName("redirect:/home/gohome.shtml");
				return modelAndView;
			}
			Integer product_stock = productService.queryProductStockById(cart.getProduct_id());
			//设置order_item的内容并添加
			order_item.setOrder_no(order_no);
			order_item.setUser_id(user.getId());
			order_item.setProduct_id(cart.getProduct().getId());
			order_item.setProduct_name(cart.getProduct().getName());
			order_item.setProduct_image(cart.getProduct().getMain_image());
			order_item.setCurrent_unit_price(cart.getProduct().getPrice());
			order_item.setQuantity(cart.getQuantity());
			order_item.setTotal_price(cart.getProduct().getPrice() * cart.getQuantity());
			order_itemService.addOrder_item(order_item);
			//计算order总金额
			payment = payment + cart.getProduct().getPrice() * cart.getQuantity();
			
			//生成订单完成后,product.stock减去对应数量,product废物利用
			product.setId(cart.getProduct().getId());
			product.setStock(product_stock - cart.getQuantity());
			productService.modifyProductStock(product);
			
			//如果存在对应cookie,删掉cartVO中的数据等待转json-----------------------
			if (cart_itemVOsList != null) {
				for (Cart_itemVO cart_itemVO : cart_itemVOsList) {
					if (cart_itemVO.getProduct().getId() == cart.getProduct_id()) {
						cart_itemVOsList.remove(cart_itemVO);
						break;
					}
				}
			}
			//-------------------------------------------------
			
		}
		//删除数据库对应cart
		cartService.deleteCartsByIdsList(idsList);
		//=======================================把删除了不少的cartVO转为json并覆盖原有cart_cookie
		if (cartVO != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			//只有对象里面不是null的才转换
			objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			StringWriter stringWriter = new StringWriter();
		    try {
				objectMapper.writeValue(stringWriter, cartVO);
			} catch (IOException e) {
				e.printStackTrace();
			}
		    Cookie newCookie = new Cookie("cart_cookie", stringWriter.toString());
		    newCookie.setMaxAge(60 * 60 * 24);
		    newCookie.setPath("/");
		    response.addCookie(newCookie);
		}
		//=======================================
		order.setUser_id(user.getId());
		order.setOrder_no(order_no);
		order.setShipping_id(shipping_id);
		order.setPayment(payment);
		order.setPayment_type(1);
		order.setPostage(0);//暂时不考虑运费
		order.setStatus(10);
		Boolean isSuccess = orderService.addOrder(order);
		if (isSuccess) {
			//把总金额和收货地址发送到付款界面
			modelAndView.addObject("payment",payment);
			modelAndView.addObject("cart_ids",cart_ids);
			/*Shipping shipping = shippingService.findShippingById(shipping_id);
			modelAndView.addObject("shipping",shipping);*/
			modelAndView.setViewName("pay");
		}else{
			modelAndView.setViewName("home");
		}
		return modelAndView;
	}
	
	@RequestMapping("paysuccess")
	public ModelAndView paySuccess(String cart_ids){
		ModelAndView modelAndView = new ModelAndView("pay_success");
		
		return modelAndView;
	}
	
	@RequestMapping("goMyOrder")
	public ModelAndView goMyOrder(String cart_ids,HttpServletRequest request){
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user") == null){
			ModelAndView modelAndView = new ModelAndView("redirect:home/gohome.shtml");
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("my_order");
		
		User user = (User) session.getAttribute("user");
		List<Order> orders = orderService.findOrderByUserId(user.getId());
		modelAndView.addObject("orders",orders);
		return modelAndView;
	}
}
