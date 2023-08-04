package com.emreoytun.customermanagementbackend.service.user;

import com.emreoytun.customermanagementdata.dto.user.UserDto;

public interface UserService {

    public boolean checkExistenceByUsername(String username);
    UserDto getByUsername(String username);
}
