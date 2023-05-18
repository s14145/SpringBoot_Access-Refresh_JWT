package com.jwtaccessrefresh.service;

import com.jwtaccessrefresh.entity.Role;
import com.jwtaccessrefresh.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Users saveUser(Users user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    Users getUser(String username);

    List<Users> getAllUsers();
}