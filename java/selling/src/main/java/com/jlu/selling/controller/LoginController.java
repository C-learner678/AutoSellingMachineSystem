package com.jlu.selling.controller;


import com.jlu.selling.bean.RespBean;
import com.jlu.selling.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @RequestMapping(value = "/login/loginIntoSystem", method = RequestMethod.POST)
    public RespBean loginIntoSystem(@RequestBody Map<String, String> params) {
        String psw = params.get("psw");
        if(loginService.checkLogin(psw)){
            return new RespBean("success", "登录成功!");
        }else{
            return new RespBean("error", "登录失败！");
        }
    }
    @RequestMapping(value = "/login/changePassword", method = RequestMethod.POST)
    public RespBean changePassword(@RequestBody Map<String, String> params) {
        String oldpsw = params.get("oldpsw");
        String newpsw = params.get("newpsw");
        if(loginService.modifyPassword(oldpsw,newpsw)){
            return new RespBean("success", "修改成功!");
        }else{
            return new RespBean("error", "修改失败！");
        }
    }
}
