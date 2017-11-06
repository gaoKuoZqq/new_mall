package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.CarouselDao;
import com.mall.pojo.Carousel;
import com.mall.service.CarouselService;
@Service
public class CarouselServiceImpl implements CarouselService{
	@Autowired
	CarouselDao carouselDao;

	@Override
	public List<Carousel> findCarousel() {
		return carouselDao.findCarousel();
	}

}
