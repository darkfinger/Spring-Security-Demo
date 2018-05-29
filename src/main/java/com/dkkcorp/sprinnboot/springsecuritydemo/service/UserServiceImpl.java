package com.dkkcorp.sprinnboot.springsecuritydemo.service;

import com.dkkcorp.sprinnboot.springsecuritydemo.model.User;
import com.dkkcorp.sprinnboot.springsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        if(user==null){
            throw new RuntimeException("null value for user");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnable(true);

        User userSaved=userRepository.save(user);
        System.out.println("============>>>>>>>>User saved : "+userSaved.getUsername());
        return userSaved;
    }
}
