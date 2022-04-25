package com.spring.redis.controller;

import com.spring.redis.entity.redisuser;
import com.spring.redis.service.UserService;
import com.spring.redis.util.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class UserControl {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUserDate")
    @ResponseBody
    public String getUserDate(Date date){
       return String.valueOf(date.getTime());
    }

    @RequestMapping("/user")
    @ResponseBody
    @LogAnnotation(eventType = "getLists", detailArgs = {"查询List数据"})
    public List<redisuser> getLists(){
        return userService.getLists();
    }



    @RequestMapping("add/")
    @ResponseBody
    public boolean add(redisuser user){
        return userService.add(user)>0;
    }
}
