package com.exam.service;

import com.exam.exception.UserNotFound;
import com.exam.model.User;
import com.exam.repo.UserRepo;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    Logger logger= LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    public UserDetails loadUserByUsername(String userName)  {

        User user= userRepo.findByUsername(userName);
        if(user==null){
            logger.error("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }
        return user;
    }
}
