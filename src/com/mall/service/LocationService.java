package com.mall.service;

import java.util.List;

import com.mall.pojo.Location;

public interface LocationService {
	
	List<Location> findProvince();
	
	List<Location> findCityByParent_id(Integer parent_id);
	
	List<Location> findAreaByParent_id(Integer parent_id);

}
