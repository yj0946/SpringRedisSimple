package com.spring.redis.version;

import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;

public interface RequestCondition<T>  {
    /**
     * 同另一个condition组合，例如，方法和类都配置了@RequestMapping的url，可以组合
     */
    T combine(T other);

    /**
     * 检查request是否匹配，可能会返回新建的对象，例如，如果规则配置了多个模糊规则，可能当前请求
     * 只满足其中几个，那么只会返回这几个条件构建的Condition
     */
    @Nullable
    T getMatchingCondition(HttpServletRequest request);

    /**
     * 比较，请求同时满足多个Condition时，可以区分优先使用哪一个
     */
    int compareTo(T other, HttpServletRequest request);
}
