package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.dao.RoleDao;
import com.ecommerce.backend.dao.UserDao;
import com.ecommerce.backend.entity.Role;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userDao.save(user);
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
