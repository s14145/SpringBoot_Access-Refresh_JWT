package com.jwtaccessrefresh.service;

import com.jwtaccessrefresh.entity.Role;
import com.jwtaccessrefresh.entity.Users;
import com.jwtaccessrefresh.repository.RoleRepository;
import com.jwtaccessrefresh.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUser = userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the database: {}", username);
        }
        Users user = optionalUser.get();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public Users saveUser(Users user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        Optional<Role> optionalRole = roleRepository.findByRoleName(roleName);
        if(!optionalRole.isPresent()){
            throw new RuntimeException("Role not found.");
        }
        Optional<Users> optionalUser = userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new RuntimeException("User not found.");
        }
        Role role = optionalRole.get();
        Users user = optionalUser.get();
        user.getRoles().add(role);
    }

    @Override
    public Users getUser(String username) {
        log.info("Fetching user {}", username);
        Optional<Users> optionalUser = userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new RuntimeException("User not found.");
        }
        Users user = optionalUser.get();
        return user;
    }

    @Override
    public List<Users> getAllUsers() {
        log.info("Fetching all users");
        Optional<List<Users>> optionalUsers = Optional.of(userRepository.findAll());
        if(!optionalUsers.isPresent()){
            throw new RuntimeException("Users not found.");
        }
        List<Users> users = optionalUsers.get();
        return users;
    }
}