package com.jwtaccessrefresh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "ROLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "ROLENAME", unique = true)
    @NotBlank(message = "Role name is a required field")
    @Size(min = 3, max = 20, message = "Role name should be between 3 and 20 characters")
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
