package com.mall.pojo;

public class Location {
	private Integer id;
	private String name;
	private Integer parent_id;
	public Location(Integer id, String name, Integer parent_id) {
		super();
		this.id = id;
		this.name = name;
		this.parent_id = parent_id;
	}
	public Location() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", parent_id=" + parent_id + "]";
	}
	
	
}
