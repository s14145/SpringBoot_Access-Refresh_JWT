package com.jwtaccessrefresh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersId;

    @Column(name = "NAME")
    @NotBlank(message = "Name is a required field")
    @Size(min = 3, max = 20, message = "Name should be between 3 and 20 characters")
    private String name;

    @Column(name = "USERNAME", unique = true)
    @NotBlank(message = "Username is a required field")
    @Size(min = 3, max = 20, message = "Username should be between 3 and 20 characters")
    private String username;

    @Column(name = "PASSWORD")
    @NotBlank(message = "Password is a required field")
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES",
              joinColumns = @JoinColumn(name =  "USER_ID", referencedColumnName = "usersId"),
              inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "roleId"))
    private List<Role> roles;

}
