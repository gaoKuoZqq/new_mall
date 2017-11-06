package com.mall.pojo;

//类别
public class Category {
	public Integer id;
	public Integer parent_id;//父类id,id=0着无父类
	public String name;
	public Integer status;//类别状态1-正常,2-已废弃
	public Integer sort_order;//排序编号,同类展示顺序,数值相等则自然排序
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSort_order() {
		return sort_order;
	}
	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}
	public Category() {
		super();
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", parent_id=" + parent_id + ", name=" + name + ", status=" + status
				+ ", sort_order=" + sort_order + "]";
	}
	
}
