package com.ljh.travels.dao;

import com.ljh.travels.entity.Place;
import org.apache.ibatis.annotations.Mapper;

import java.net.Inet4Address;
import java.util.List;

@Mapper
public interface PlaceDao extends BaseDao<Place,String> {
    // 因为是根据景点id查询的,所以这里不能直接用BaseDao里的分页
    List<Place> findByProvinceIdPage(Integer start, Integer rows, String provinceId);

    // 因为是根据景点id查询的,所以这里不能直接用BaseDao里的查询所有
    Integer findByProvinceIdCounts(String provinceId);

    void save(Place place);
}
