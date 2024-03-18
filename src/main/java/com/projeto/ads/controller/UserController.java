package com.projeto.ads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

    @GetMapping("/Login/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
    mv.setViewName("Login/login");
        return mv;
    }
}
