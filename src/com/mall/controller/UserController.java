package com.mall.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.pojo.Cart;
import com.mall.pojo.User;
import com.mall.responce.ServerResponse;
import com.mall.service.CartService;
import com.mall.service.ProductService;
import com.mall.service.UserService;
import com.mall.vo.CartVO;
import com.mall.vo.Cart_itemVO;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<?> addUser(User user) {
		if (user == null || user.getUsername() == null || user.getUsername().trim().equals("")
				|| user.getPassword() == null || user.getPassword().trim().equals("")
				|| user.getEmail() == null || user.getEmail().trim().equals("") 
				|| user.getQuestion() == null || user.getQuestion().trim().equals("") 
				|| user.getAnswer() == null || user.getAnswer().trim().equals("") ) {
			return ServerResponse.createError("请完整填写表单");
		}
		user.setRole(1);
		return userService.addUser(user);
	}
	@RequestMapping("goadd")
	public ModelAndView goAddUser(){
		ModelAndView modelAndView = new ModelAndView("register");
		return modelAndView;
	}
	
	@RequestMapping("checkname")
	@ResponseBody
	public Boolean checkName(String username) {
		return userService.checkName(username);
	}
	
	@RequestMapping("login")
	@ResponseBody
	public Boolean checkLogin(User user, HttpServletRequest request) {
		if (user.getUsername() == null || user.getUsername().trim().equals("") || 
				user.getPassword() == null || user.getPassword().trim().equals("")) {
			return false;
		}
		Boolean isSuccess = userService.checkLogin(user);
		if(isSuccess){
			HttpSession session = request.getSession(true);
			user.setPassword(null);
			user.setId(userService.findUserIdByUsername(user.getUsername()));
			session.setAttribute("user", user);
			
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("cart_cookie".equals(cookie.getName())) {
						ObjectMapper objectMapper = new ObjectMapper();
						objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
						CartVO cartVO = new CartVO();
						String value = cookie.getValue();
						try {
							cartVO = objectMapper.readValue(value, CartVO.class);
						} catch (IOException e) {
							e.printStackTrace();
						}
						List<Cart_itemVO> cart_itemVOsList = cartVO.getCart_itemVOsList();
						Cart cart = new Cart();
						cart.setUser_id(user.getId());
						for (Cart_itemVO cart_itemVO : cart_itemVOsList) {
							Integer product_id = cart_itemVO.getProduct().getId();
							Integer product_stock = productService.queryProductStockById(product_id);
							cart.setProduct_id(product_id);
							cart.setQuantity(cart_itemVO.getQuantity());
							cart.setChecked(0);
							//判断是否存在重复cart
							Cart oldCart = cartService.findCartByNewCart(cart);
							//如果已存在同产品cart,更改数量
							if (oldCart != null) {
								if (cart.getQuantity() > product_stock) {
									oldCart.setQuantity(product_stock);
								}else {
									oldCart.setQuantity(oldCart.getQuantity() + cart.getQuantity());
								}
								cartService.modifyCartQuantity(oldCart);
							}else {
								cartService.addCart(cart);
							}
						}
						break;
					}
				}
			}
 		}
		return isSuccess;
	}
	@RequestMapping("gologin")
	public ModelAndView goLogin(){
		ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping("logout")
	@ResponseBody
	public Boolean logout(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		session.setAttribute("user", null);
		return true;
	}
	
}
