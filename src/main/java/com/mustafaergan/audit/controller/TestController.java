package com.mustafaergan.audit.controller;

import com.mustafaergan.audit.domain.User;
import com.mustafaergan.audit.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : MustafaERGAN
 * Date: 4.10.2018 09:53
 */
@RestController
@RequestMapping(path ="/test")
public class TestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/update")
    public String sayHello(){
        User user = new User();
        user.setUsername("mustafa");
        userRepository.save(user);
        user.setName("mehmet");
        userRepository.save(user);
        user.setUsername("Hasan");
        userRepository.save(user);
        return "save and update success";
    }


}
