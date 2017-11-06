package com.mall.pojo;

import java.sql.Date;

public class Pay_info {
	Integer id;
	Integer user_id;
	String order_no;//订单号
	Integer pay_platform;//支付平台
	String platform_number;//支付流水号
	String platform_status;//支付状态
	//这里我直接使用了sql的Date,构造方法有改动,添加了java.util.Date的set
	Date create_time;
	Date update_time;
	public Pay_info() {
		super();
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
	public Integer getPay_platform() {
		return pay_platform;
	}
	public void setPay_platform(Integer pay_platform) {
		this.pay_platform = pay_platform;
	}
	public String getPlatform_number() {
		return platform_number;
	}
	public void setPlatform_number(String platform_number) {
		this.platform_number = platform_number;
	}
	public String getPlatform_status() {
		return platform_status;
	}
	public void setPlatform_status(String platform_status) {
		this.platform_status = platform_status;
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
	@Override
	public String toString() {
		return "Pay_info [id=" + id + ", user_id=" + user_id + ", order_no=" + order_no + ", pay_platform="
				+ pay_platform + ", platform_number=" + platform_number + ", platform_status=" + platform_status
				+ ", create_time=" + create_time + ", update_time=" + update_time + "]";
	}
	
}
