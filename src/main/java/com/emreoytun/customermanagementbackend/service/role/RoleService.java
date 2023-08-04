package com.emreoytun.customermanagementbackend.service.role;

import com.emreoytun.customermanagementdata.entities.Role;

public interface RoleService {

    public Role findByName(String roleName);
}
