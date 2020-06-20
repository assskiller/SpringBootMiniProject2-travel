package com.ljh.travels.service;

import com.ljh.travels.entity.User;

public interface UserService {
    void register(User user);
    User login(User user);
}
