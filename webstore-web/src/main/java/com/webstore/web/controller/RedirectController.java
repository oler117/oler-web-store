package com.webstore.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by oles on 8/1/2016.
 * <p>
 * Controller for fixing Angular html5mode
 */
@Controller
public class RedirectController {

    @RequestMapping(value = "/*")
    public String redirect() {
        return "app/index.html";
    }

}
