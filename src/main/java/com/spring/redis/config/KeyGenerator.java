package com.spring.redis.config;

import java.lang.reflect.Method;

public class KeyGenerator implements org.springframework.cache.interceptor.KeyGenerator{
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        StringBuilder sb = new StringBuilder();
        sb.append(o.getClass().getName());
        sb.append("-");
        sb.append(method.getName());
        sb.append("-");
        for (Object param : objects) {
            sb.append(param.toString());
        }
        return sb.toString();
    }
}
