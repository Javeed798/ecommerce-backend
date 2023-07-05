package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.dao.RoleDao;
import com.ecommerce.backend.entity.Role;
import com.ecommerce.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role createNewRole(Role role) {
        Role save = roleDao.save(role);
        return save;
    }
}
