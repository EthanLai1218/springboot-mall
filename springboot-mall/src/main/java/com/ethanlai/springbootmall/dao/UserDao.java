package com.ethanlai.springbootmall.dao;

import com.ethanlai.springbootmall.dto.UserRegisterRequest;
import com.ethanlai.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
