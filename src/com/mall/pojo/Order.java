package com.mall.pojo;

import java.sql.Date;

public class Order {
	Integer id;
	//不想用long
	String order_no;//订单号
	Integer user_id;
	Integer shipping_id;//'shipping'指地址电话等收货信息
	double payment;//实际付款金额
	Integer payment_type;//支付类型,现仅有在线支付
	Integer postage;//运费
	Integer status;//订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
	Date payment_time;//支付时间
	Date send_time;//发货时间
	Date end_time;//交易完成时间
	Date close_time;//交易关闭时间
	//这里我直接使用了java.sql的Date,构造方法有改动,添加了java.util.Date的set
	Date create_time;
	Date update_time;
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", order_no=" + order_no + ", user_id=" + user_id + ", shipping_id=" + shipping_id
				+ ", payment=" + payment + ", payment_type=" + payment_type + ", postage=" + postage + ", status="
				+ status + ", payment_time=" + payment_time + ", send_time=" + send_time + ", end_time=" + end_time
				+ ", close_time=" + close_time + ", create_time=" + create_time + ", update_time=" + update_time + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getShipping_id() {
		return shipping_id;
	}
	public void setShipping_id(Integer shipping_id) {
		this.shipping_id = shipping_id;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public Integer getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(Integer payment_type) {
		this.payment_type = payment_type;
	}
	public Integer getPostage() {
		return postage;
	}
	public void setPostage(Integer postage) {
		this.postage = postage;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(Date payment_time) {
		this.payment_time = payment_time;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Date getClose_time() {
		return close_time;
	}
	public void setClose_time(Date close_time) {
		this.close_time = close_time;
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
	public Order() {
		super();
	}
}
