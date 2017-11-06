package com.mall.pojo;

import java.sql.Date;

public class Product {
	Integer id;
	Integer category_id;//分类id,对应category的id
	//-------------------------------------------
	//由于展示需要,我在下面写了category的get和set方法,尽管数据库没有这条
	String category_name;
	//--------------------------------------------
	String name;//商品名称
	String subtitle;//商品副标题
	String main_image;//产品主图,url相对地址
	String sub_images;//图片地址,json格式,扩展用
	String detail;//商品详情
	Double price;//价格
	Integer stock;//库存
	Integer status;//商品状态.1-在售 2-下架 3-删除
	Date create_time;
	Date update_time;
	public Product() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getMain_image() {
		return main_image;
	}
	public void setMain_image(String main_image) {
		this.main_image = main_image;
	}
	public String getSub_images() {
		return sub_images;
	}
	public void setSub_images(String sub_images) {
		this.sub_images = sub_images;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
		return "Product [id=" + id + ", category_id=" + category_id + ", category_name=" + category_name + ", name="
				+ name + ", subtitle=" + subtitle + ", main_image=" + main_image + ", sub_images=" + sub_images
				+ ", detail=" + detail + ", price=" + price + ", stock=" + stock + ", status=" + status
				+ ", create_time=" + create_time + ", update_time=" + update_time + "]";
	}
}
