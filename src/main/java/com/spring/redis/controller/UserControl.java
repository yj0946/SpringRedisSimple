package com.spring.redis.controller;

import com.spring.redis.entity.UserExample;
import com.spring.redis.entity.redisuser;
import com.spring.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserControl {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    @ResponseBody
    public List<redisuser> getLists(){
        return userService.getLists();
    }
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(redisuser user){
        return userService.add(user)>0;
    }
}
