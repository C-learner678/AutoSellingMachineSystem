package com.jlu.selling.service;

import com.jlu.selling.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginMapper loginMapper;
    public boolean checkLogin(String password){
        if(loginMapper.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }
    public boolean modifyPassword(String oldPassword, String newPassword){
        if(loginMapper.getPassword().equals(oldPassword)){
            if(loginMapper.modifyPassword(oldPassword, newPassword) == 1){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }
}
