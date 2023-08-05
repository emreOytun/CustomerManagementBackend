package com.emreoytun.customermanagementbackend.service.role;

import com.emreoytun.customermanagementbackend.entities.Role;
import com.emreoytun.customermanagementbackend.repository.RoleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleManager implements RoleService {

    private final RoleDao roleDao;

    @Override
    public Role findByName(String roleName) {
        return roleDao.findByName(roleName);
    }
}
