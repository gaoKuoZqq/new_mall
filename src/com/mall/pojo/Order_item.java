package com.mall.pojo;

import java.sql.Date;

public class Order_item {
	Integer id;//订单子表id
	Integer user_id;
	String order_no;//订单号
	Integer product_id;//商品id
	String product_name;
	String product_image;//商品图片地址
	double current_unit_price;//生成订单时的商品单价
	Integer quantity;//数量
	double total_price;//总价
	Date create_time;
	Date update_time;
	public Order_item() {
		super();
	}
	@Override
	public String toString() {
		return "Order_item [id=" + id + ", user_id=" + user_id + ", order_no=" + order_no + ", product_id=" + product_id
				+ ", product_name=" + product_name + ", product_image=" + product_image + ", current_unit_price="
				+ current_unit_price + ", quantity=" + quantity + ", total_price=" + total_price + ", create_time="
				+ create_time + ", update_time=" + update_time + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public double getCurrent_unit_price() {
		return current_unit_price;
	}
	public void setCurrent_unit_price(double current_unit_price) {
		this.current_unit_price = current_unit_price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}
