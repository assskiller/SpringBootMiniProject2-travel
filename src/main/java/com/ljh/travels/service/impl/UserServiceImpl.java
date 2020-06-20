package com.ljh.travels.service.impl;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import com.ljh.travels.dao.UserDao;
import com.ljh.travels.entity.User;
import com.ljh.travels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        System.out.println(userDao.findByUsername(user.getUsername()));
        if(userDao.findByUsername(user.getUsername()) == null){
            userDao.save(user);
        }
        else{
            throw new RuntimeException("用户名已存在");
        }
    }

    @Override
    public User login(User user) {
        User findUser = userDao.findByUsername(user.getUsername());
        if(findUser==null)
        {
            throw new RuntimeException("用户名或密码错误");
        }
        else
        {
            if(findUser.getPassword().equals(user.getPassword()))
            {
                return findUser;
            }
            else
            {
                throw new RuntimeException("用户名或密码错误");
            }
        }
    }

}
