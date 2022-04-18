package com.spring.redis.service.impl;

import com.spring.redis.entity.User;
import com.spring.redis.entity.UserExample;
import com.spring.redis.entity.redisuser;
import com.spring.redis.mapper.UserMapper;
import com.spring.redis.service.UserService;
import com.spring.redis.util.LogAnnotationAspect;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final Logger loggerUserServiceImpl = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Cacheable(value="user")
    @Override
    public List<redisuser> getLists() {
        //System.out.println("打印语句则没有走缓存");
        loggerUserServiceImpl.info("打印语句则没有走缓存");
        return userMapper.getLists();
    }

    @CacheEvict(value= "user",allEntries=true)//清空缓存，
    @Override
    public Integer add(redisuser user) {
        return userMapper.add(user);
    }
}
