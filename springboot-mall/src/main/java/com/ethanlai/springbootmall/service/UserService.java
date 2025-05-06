package com.ethanlai.springbootmall.service;

import com.ethanlai.springbootmall.dto.UserLoginRequest;
import com.ethanlai.springbootmall.dto.UserRegisterRequest;
import com.ethanlai.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);
}
