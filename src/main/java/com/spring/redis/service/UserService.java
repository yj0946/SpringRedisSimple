package com.spring.redis.service;

import com.spring.redis.entity.User;
import com.spring.redis.entity.UserExample;
import com.spring.redis.entity.redisuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    List<redisuser> getLists();
    Integer add(redisuser user);
}
