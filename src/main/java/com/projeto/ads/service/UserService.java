package com.projeto.ads.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.projeto.ads.model.User;
import com.projeto.ads.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String validateErrors (User user, String confirmPassword, String birthDateString) {
        String error = null;

        if(!user.getPassword().equals(confirmPassword)) {
            error = "As senhas não conferem";
            return error;
        }

        if(userRepository.findByUsername(user.getUsername()) != null) {
            error = "Nome de usuário já cadastrado";
            return error;
        }

        if(userRepository.findByEmail(user.getEmail()) != null) {
            error = "Email já cadastrado";
            return error;
        }

        if(user.getPassword().length() < 8) {
            error = "A senha deve ter no mínimo 8 caracteres";
            return error;
        }

        if (this.validateBirthDate(birthDateString)) {
            error = "Data de nascimento inválida";
            return error;
        }

        return error;
    }

    public boolean validateBirthDate (String birthDateString) {
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();

        String[] date = birthDateString.split("-");
        int birthYear =  Integer.parseInt(date[0]);
        int birthMonth = Integer.parseInt(date[1]);
        int birthDay = Integer.parseInt(date[2]);

        if(birthYear > year) {
            return true;
        } 
        
        if(birthYear == year) {

            if(birthMonth > month) {
                return true;
            }  
            
            if(birthMonth == month) {

                if(birthDay > day) {
                    return true;
                }

            }
        }

        return false;
    }

}
