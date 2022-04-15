package com.spring.redis.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *类功能简述:	日志切面
 * 类功能详述:业务模块的操作需要写日志，通过日志切面，无缝地将写日志功能插入到业务模块的方法中，
 * 而不需要修改原有业务代码，对于业务模块而言，日志的写入是透明的，可随时撤走。
 *
 * 增、删、改 -----自定义注解配置需要以下面的开头
 * 增： insert  [String, Class] ---用户userId新增Class信息
 * 删除:del [String,Class] --用户userId删除Class信息
 * 改：modify [String, Class1,Class2] --用户userId修改class1变成class2
 */

@Aspect
@Component
public class LogAnnotationAspect {

    private final Logger logger = Logger.getLogger(LogAnnotationAspect.class);

    // 一分钟，即1000ms
    private static final long ONE_MINUTE = 100;

    /**
     *  定义事件日志切入点，凡是应用了LogAnnotation注解的方法都会被写日志
     */
    @Pointcut("@annotation(com.spring.redis.util.LogAnnotation)")
    public void eventLog() {}

    /**
     *  进入方法后打印日志
     * @param joinPoint
     */
    @Before("eventLog()")
    public void before(JoinPoint joinPoint) {
        logger.info(this.getMethodName(joinPoint)+" start "+ DateUtil.toString(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 方法结束打印日志
     * @param joinPoint
     */
    @After("eventLog()")
    public void after(JoinPoint joinPoint) {
        logger.info(this.getMethodName(joinPoint)+" after"+ DateUtil.toString(DateUtil.now(), "yyyy-MM-dd HH:mm:ss"));
    }

    @Around("execution(* com.spring.redis.controller.*.*(..))")
    public Object processLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();

        try {
            obj = joinPoint.proceed(args);
        } catch (Throwable e) {
            logger.error("统计某方法执行耗时环绕通知出错", e);
        }

        // 获取执行的方法名
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        // 打印耗时的信息
        this.printExecTime(methodName, startTime, endTime);

        return obj;
    }

    /**
     * 打印方法执行耗时的信息，如果超过了一定的时间，才打印
     * @param methodName
     * @param startTime
     * @param endTime
     */
    private void printExecTime(String methodName, long startTime, long endTime) {
        long diffTime = endTime - startTime;
        if (diffTime > ONE_MINUTE) {
            logger.info( methodName + " 方法执行耗时：" + diffTime + " ms");
        }
    }

    /**
     * AfterReturning  拦截执行
     */
    @AfterReturning("execution(* com.spring.redis.controller.*.*(..))")
    public void AfterReturning() {
        logger.info("method AfterReturning");
    }


    /**
     *  AfterThrowing 拦截执行
     */
    @AfterThrowing("execution(* com.spring.redis.controller.*.*(..))")
    public void AfterThrowing() {
        logger.info("method AfterThrowing");
    }


    /**
     * 获取方法名(类的详细包路径)
     * @param joinPoint
     * @return
     */
    private String getMethodName(JoinPoint joinPoint){
        return joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName();
    }
}
