package com.jwtaccessrefresh;

import com.jwtaccessrefresh.entity.Role;
import com.jwtaccessrefresh.entity.Users;
import com.jwtaccessrefresh.repository.RoleRepository;
import com.jwtaccessrefresh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootAccessRefreshJwtApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAccessRefreshJwtApplication.class, args);
	}

	@PostConstruct
	public void initDatabaseData(){
		Role role = new Role("USER");
		Role role1 = new Role("ADMIN");
		List<Role> roles = Arrays.asList(role, role1);
		roleRepository.saveAll(roles);

		Users user = new Users("RAM","RAM@GMAIL.COM","$2a$12$CuS8EmIefLZErA3xYWoaOupsRxboTsnYF.BIDDpv8L7apR6VJwo8q", roles);
		userRepository.save(user);
	}

}
