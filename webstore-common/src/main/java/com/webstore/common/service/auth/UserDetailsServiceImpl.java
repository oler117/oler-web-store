package com.webstore.common.service.auth;

import com.webstore.common.model.auth.User;
import com.webstore.common.repository.jpa.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by oles on 7/27/2016.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("There is no user with username: " + username);
        } else {
            return new UserDetailsImpl(user);
        }
    }
}
