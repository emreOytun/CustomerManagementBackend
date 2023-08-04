package com.emreoytun.customermanagementbackend.controllers;

import com.emreoytun.customermanagementbackend.service.user.UserService;
import com.emreoytun.customermanagementdata.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserByUsername(@PathVariable("username") String username) {
        return userService.getByUsername(username);
    }


}
