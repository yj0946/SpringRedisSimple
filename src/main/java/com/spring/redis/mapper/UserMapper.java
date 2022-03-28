package com.spring.redis.mapper;

import com.spring.redis.entity.User;
import com.spring.redis.entity.UserExample;
import com.spring.redis.entity.redisuser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<redisuser> getLists();
    Integer add(redisuser user);
}
