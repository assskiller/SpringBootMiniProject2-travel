package com.ljh.travels.service;

import com.ljh.travels.entity.Province;

import java.util.List;

public interface ProvinceService {

    List<Province> findByPage(Integer page,Integer rows);
    Integer findTotals();
    void save(Province province);
    void delete(String id);
    Province findOne(String id);
    void update(Province province);

}
