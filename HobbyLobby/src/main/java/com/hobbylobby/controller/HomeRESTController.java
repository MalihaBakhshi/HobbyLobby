package com.hobbylobby.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobbylobby.domain.User;
import com.hobbylobby.service.UserService;

@RestController
@RequestMapping("/api")
public class HomeRESTController {

    @Autowired
    private UserService userService;

    @GetMapping("/isunique/{username}")
    public boolean isUniqueUsername (@PathVariable String username) {

        List<User> userOpt = userService.findUserByUsername(username);


        if(userOpt.size()==1) {
            // System.out.println("falseeeee");
            return false;
        } else {
            // System.out.println("trueeeeeee");
            return true;
        }
    }

    @GetMapping("/getname/{userId}")
    public String islikedPost (@AuthenticationPrincipal User user, @PathVariable Long userId) {

        Optional<User> userOpt = userService.findUserById(userId);

        if(userOpt.isPresent()) {
            System.out.println(userOpt.get().getUsername());
            return userOpt.get().getUsername();
        } else {
            System.out.println("falseeeeeee");
            return "Name";
        }
    }
}
