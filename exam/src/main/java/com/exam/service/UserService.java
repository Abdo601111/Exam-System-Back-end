package com.exam.service;

import com.exam.exception.UserFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepo;
import com.exam.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepoo;
    @Autowired
    private RoleRepo roleRepo;

    public User createUser(User user, Set<UserRole> userRepos) throws Exception {
        User local=  userRepoo.findByUsername(user.getUsername());
        if(local!=null){
            logger.info("User Is Already Exist");
            throw new UserFoundException();
        }else {
             userRepos.forEach(s-> roleRepo.save(s.getRole()));
            user.setEnable(true);
             user.getUserRole().addAll(userRepos);
            local= userRepoo.save(user);

        }
        return local;

    }

    public User getUser(String username) {
        return  userRepoo.findByUsername(username);
    }
    public void deleteUser(long userId){
        userRepoo.deleteById(userId);
    }
}
