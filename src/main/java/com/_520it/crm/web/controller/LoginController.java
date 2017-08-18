package com._520it.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author
 * @date 2017/7/12
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String main(){
        return "login";
    }
}
