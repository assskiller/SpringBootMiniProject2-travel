package com.ljh.travels.dao;

import com.ljh.travels.entity.Province;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProvinceDao extends BaseDao<Province,String>  {
    Province findOneByID(String id);
}
