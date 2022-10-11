package com.exam.controller;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController()
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public User saveUser( @RequestBody User user) throws Exception {


        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> userRoleSet= new HashSet<>();


        Role role= new Role();
        role.setId(45l);
        role.setRoleName("NORMAL");




        UserRole userRole= new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);


        userRoleSet.add(userRole);



        return userService.createUser(user,userRoleSet);
    }

    @GetMapping("/{username}")
    public User loadUserByUsername(@PathVariable("username") String username){
        return userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") long userId){
         userService.deleteUser(userId);
    }
}
