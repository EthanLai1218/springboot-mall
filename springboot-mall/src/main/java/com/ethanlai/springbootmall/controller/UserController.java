package com.ethanlai.springbootmall.controller;

import com.ethanlai.springbootmall.dto.UserLoginRequest;
import com.ethanlai.springbootmall.dto.UserRegisterRequest;
import com.ethanlai.springbootmall.model.User;
import com.ethanlai.springbootmall.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "使用者管理", description = "提供會員註冊與登入功能")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "會員註冊",
            description = "使用者填寫 email、密碼等資訊來註冊帳號"
    )
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {

        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(
            summary = "會員登入",
            description = "使用者輸入 email 與密碼進行登入驗證"
    )
    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest  userLoginRequest ) {

        User user = userService.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
