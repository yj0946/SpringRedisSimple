package com.spring.redis.controller;

import com.spring.redis.entity.UserExample;
import com.spring.redis.entity.redisuser;
import com.spring.redis.service.UserService;
import com.spring.redis.version.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@ApiVersion
@RequestMapping("/api/{version}")
public class UserControl {
    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    @ApiVersion(2)
    @ResponseBody
    public List<redisuser> getLists(){
        return userService.getLists();
    }

    @RequestMapping("/user")
    @ApiVersion(4)
    @ResponseBody
    public List<redisuser> getListsV4(){
        return null;
    }

    @RequestMapping("/add")
    @ResponseBody
    public boolean add(redisuser user){
        return userService.add(user)>0;
    }
}
