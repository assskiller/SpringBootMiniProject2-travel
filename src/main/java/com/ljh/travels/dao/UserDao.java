package com.ljh.travels.dao;

import com.ljh.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper //mapeer注解，让spring知道这个dao
public interface UserDao {
    void save(User user);
    User findByUsername(String username);
}
