package com.dzforever.controller;

import com.dzforever.entity.Result;
import com.dzforever.entity.StateCode;
import com.dzforever.pojo.User;
import com.dzforever.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public Result login(User user) {
        User theUser = userService.login(user);
        if (theUser == null) {
            System.out.println("错误");
            return new Result(true, StateCode.OK,"用户名或密码错误",0);
        }
        else {
            return new Result(true, StateCode.OK, "登陆成功", 1);
        }
    }

}
