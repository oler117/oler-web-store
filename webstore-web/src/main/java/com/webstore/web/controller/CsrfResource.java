package com.webstore.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;

/**
 * Created by oler117 on 30.07.2016.
 */
@RestController
public class CsrfResource {

    @RequestMapping(value = "/csrf", method = RequestMethod.GET)
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }

    @ExceptionHandler(HttpSessionRequiredException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The session has expired")
    public String handleSessionExpired() {
        return "sessionExpired";
    }
}
