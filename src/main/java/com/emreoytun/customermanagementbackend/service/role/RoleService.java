package com.emreoytun.customermanagementbackend.service.role;

import com.emreoytun.customermanagementbackend.entities.Role;

public interface RoleService {

    public Role findByName(String roleName);
}
