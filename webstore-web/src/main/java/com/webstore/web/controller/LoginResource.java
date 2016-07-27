package com.webstore.web.controller;

import com.webstore.common.model.auth.User;
import com.webstore.common.repository.jpa.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by oles on 7/26/2016.
 */
@RestController
public class LoginResource {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity test() {

        List<User> all = userRepository.findAll();
        return ResponseEntity.ok(all);
    }

//    @RequestMapping(value="/logout", method = RequestMethod.POST)
//    public String logout (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/";
//    }

}
