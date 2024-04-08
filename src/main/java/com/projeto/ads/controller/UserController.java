package com.projeto.ads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.projeto.ads.model.User;
import com.projeto.ads.repository.RoleRepository;

@Controller
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new User());
        mv.setViewName("Login/login");
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new User());
        mv.addObject("roles", roleRepository.findAll());
        mv.setViewName("Login/register");
        return mv;
    }
}
