package com.ethanlai.springbootmall.service.impl;

import com.ethanlai.springbootmall.dao.UserDao;
import com.ethanlai.springbootmall.dto.UserRegisterRequest;
import com.ethanlai.springbootmall.model.User;
import com.ethanlai.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
