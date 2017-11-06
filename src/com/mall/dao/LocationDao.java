package com.mall.dao;

import java.util.List;

import com.mall.pojo.Location;

public interface LocationDao {
	
	List<Location> findProvince();
	
	List<Location> findCityByParent_id(Integer parent_id);
	
	List<Location> findAreaByParent_id(Integer parent_id);

}
