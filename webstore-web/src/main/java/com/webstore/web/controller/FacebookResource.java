package com.webstore.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by oles on 8/3/2016.
 */
@RestController
@RequestMapping("connect/facebook")
public class FacebookResource {

    @Autowired
    private ConnectionRepository connectionRepository;
    @Autowired
    private Facebook facebook;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public User fetchProfile() {

        User profile = null;
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);

        if (connection != null) {
            profile = connection.getApi().userOperations().getUserProfile();
//            profile = facebook.userOperations().getUserProfile();
        }

        return profile;
    }
}
