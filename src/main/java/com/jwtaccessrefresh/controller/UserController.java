package com.jwtaccessrefresh.controller;

import com.jwtaccessrefresh.entity.Role;
import com.jwtaccessrefresh.entity.Users;
import com.jwtaccessrefresh.model.RoleToUserForm;
import com.jwtaccessrefresh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/users/{username}")
    public ResponseEntity<Users> getUser(@PathVariable String username){
      Users user = userService.getUser(username);
      return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<Users>> getUsers(){
        List<Users> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<Users> saveUser(@RequestBody Users user){
      Users saveUser = userService.saveUser(user);
      return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @PostMapping(value = "/roles")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        Role savedRole = userService.saveRole(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    @PostMapping(value = "/roles/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm){
        userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}