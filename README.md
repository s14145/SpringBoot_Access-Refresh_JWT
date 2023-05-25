# Spring Boot Access &amp; Refresh JWT

This application starts on port 6064.

The technologies used in this application are:

1. Spring Boot Web
2. Spring Boot Data JPA
3. Spring Security
4. JWT (JSON Web Token)
5. Spring Boot Devtools
6. Lombok
7. Hibernate
8. H2 Database

H2 Database URI: http://localhost:6064/h2-console

The REST endpoint exposed by this application are:
1. POST Login (Retrieve Access and Refresh Token): http://localhost:6064/login

**Note for below REST endpoint : Access or Refresh token needs to be passed in request header using "Authorization = Bearer << Access or Refresh Token >>")**

2. GET User: http://localhost:6064/api/v1/users/{username}

3. GET Users: http://localhost:6064/api/v1/users

4. POST User: http://localhost:6064/api/v1/users

5. POST Role: http://localhost:6064/api/v1/roles

6. POST Add Role To User: http://localhost:6064/api/v1/roles

Sample request payloads:

For POST User

{

    "name": "Shyam",
    "username": "Shyam123",
    "password": "1234ten",
    "roles": [
        {
            "roleName": "USER"
    }]
    
}

For POST Role

{

    "roleName": "USER" 
    
}

For POST Add Role To User

{

    "username": "Gita",
    "roleName": "USER"
    
}


