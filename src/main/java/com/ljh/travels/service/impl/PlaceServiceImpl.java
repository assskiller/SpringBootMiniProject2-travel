package com.ljh.travels.service.impl;

import com.ljh.travels.dao.PlaceDao;
import com.ljh.travels.entity.Place;
import com.ljh.travels.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceDao placeDao;

    @Override
    public List<Place> findPageByProvinceId(Integer page, Integer rows, String provinceId) {
        int start = (page-1)*rows;
        return placeDao.findByProvinceIdPage(start,rows,provinceId);
    }

    @Override
    public Integer findTotalCountsByProvinceId(String provinceId) {
        return placeDao.findByProvinceIdCounts(provinceId);
    }
}
