package com.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.pojo.Location;
import com.mall.service.LocationService;

@Controller
@RequestMapping("location")
public class LocationController {
	@Autowired
	LocationService locationService;
	
	@RequestMapping("findcity")
	@ResponseBody
	public List<Location> findCityByParent_id(Integer parent_id){
		return locationService.findCityByParent_id(parent_id);
	}
	
	@RequestMapping("findarea")
	@ResponseBody
	public List<Location> findAreaByParent_id(Integer parent_id){
		return locationService.findAreaByParent_id(parent_id);
	}
}
