package com.mall.pojo;

import java.sql.Date;

public class Shipping {
	Integer id;
	Integer user_id;
	String receiver_name;//收货人姓名
	String receiver_phone;//收货人固定电话
	String receiver_mobile;//收货人移动电话
	String receiver_province;//省份
	String receiver_city;//城市
	String receiver_district;//区/县
	String receiver_address;//详细地址
	String receiver_zip;//邮编
	//这里我直接使用了java.sql的Date,构造方法有改动,添加了java.util.Date的set
	Date create_time;
	Date update_time;
	public Shipping() {
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
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}
	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	public String getReceiver_mobile() {
		return receiver_mobile;
	}
	public void setReceiver_mobile(String receiver_mobile) {
		this.receiver_mobile = receiver_mobile;
	}
	public String getReceiver_province() {
		return receiver_province;
	}
	public void setReceiver_province(String receiver_province) {
		this.receiver_province = receiver_province;
	}
	public String getReceiver_city() {
		return receiver_city;
	}
	public void setReceiver_city(String receiver_city) {
		this.receiver_city = receiver_city;
	}
	public String getReceiver_district() {
		return receiver_district;
	}
	public void setReceiver_district(String receiver_district) {
		this.receiver_district = receiver_district;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public String getReceiver_zip() {
		return receiver_zip;
	}
	public void setReceiver_zip(String receiver_zip) {
		this.receiver_zip = receiver_zip;
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
		return "Shipping [id=" + id + ", user_id=" + user_id + ", receiver_name=" + receiver_name + ", receiver_phone="
				+ receiver_phone + ", receiver_mobile=" + receiver_mobile + ", receiver_province=" + receiver_province
				+ ", receiver_city=" + receiver_city + ", receiver_district=" + receiver_district
				+ ", receiver_address=" + receiver_address + ", receiver_zip=" + receiver_zip + ", create_time="
				+ create_time + ", update_time=" + update_time + "]";
	}
	
}
