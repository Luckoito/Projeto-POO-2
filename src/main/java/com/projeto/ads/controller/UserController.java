package com.projeto.ads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.projeto.ads.model.User;
import com.projeto.ads.model.Role;
import com.projeto.ads.repository.RoleRepository;
import com.projeto.ads.repository.UserRepository;


@Controller
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @PostMapping("/register")
    public ModelAndView store(
        @ModelAttribute User user,
        @RequestParam("confirmPassword") String confirmPassword,
        @RequestParam("birthDate") String birthDateString
    ) {
        ModelAndView mv = new ModelAndView();

        if(!user.getPassword().equals(confirmPassword)) {
            mv.addObject("error", "As senhas não conferem");
            mv.addObject("user", user);
            mv.setViewName("Login/register");
            return mv;
        }

        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(birthDateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        user.setBirthDate(date);
        user.setPassword(passwordEncoder.encode(confirmPassword));
        user.setActive(true);

        Role role = roleRepository.findByName("ROLE_USER");
        user.setRole(role);

        userRepository.save(user);

        mv.setViewName("redirect:/login");
        return mv;
    }
    
}
