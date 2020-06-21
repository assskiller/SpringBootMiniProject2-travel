package com.ljh.travels.service;


import com.ljh.travels.entity.Place;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaceService {
    public List<Place> findPageByProvinceId(Integer page, Integer rows, String provinceId);
    public Integer findTotalCountsByProvinceId(String provinceId);

    void save(Place place);
}
