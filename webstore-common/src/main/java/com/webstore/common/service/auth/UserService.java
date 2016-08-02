package com.webstore.common.service.auth;

import com.webstore.common.model.auth.User;

/**
 * Created by oler117 on 30.07.2016.
 */
public interface UserService {

    User getUserByUsername(String username);

    void createNewUser(User newUser);

    boolean activateAccount(Integer userId, String token);

}
