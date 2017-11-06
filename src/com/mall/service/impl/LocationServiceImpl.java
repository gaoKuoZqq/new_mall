package com.mall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.dao.LocationDao;
import com.mall.pojo.Location;
import com.mall.service.LocationService;
@Service("locationService")
public class LocationServiceImpl implements LocationService{
	@Resource(name="locationDao")
	LocationDao locationDao;
	
	@Override
	public List<Location> findProvince() {
		return locationDao.findProvince();
	}

	@Override
	public List<Location> findCityByParent_id(Integer parent_id) {
		return locationDao.findCityByParent_id(parent_id);
	}

	@Override
	public List<Location> findAreaByParent_id(Integer parent_id) {
		return locationDao.findAreaByParent_id(parent_id);
	}
	
}
