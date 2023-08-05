package com.emreoytun.customermanagementbackend.service.user;

import com.emreoytun.customermanagementdata.dto.ModelMapperService;
import com.emreoytun.customermanagementdata.dto.role.RoleDto;
import com.emreoytun.customermanagementdata.dto.user.UserDto;
import com.emreoytun.customermanagementbackend.entities.User;
import com.emreoytun.customermanagementbackend.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserDao userDao;
    private final ModelMapperService modelMapperService;

    @Override
    public boolean checkExistenceByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    @Override
    public UserDto getByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return null;
        }

        UserDto userDto = modelMapperService.mapComposed(user, UserDto.class);
        userDto.setRoles(new HashSet<>());
        if (user.getRoles() != null) {
            user.getRoles().stream().forEach(role -> userDto.getRoles().add(modelMapperService.map(role, RoleDto.class)));
        }
        return userDto;
    }
}
