package com.winterarm.aspect.aspect;

import com.alibaba.fastjson.JSONObject;
import com.winterarm.aspect.annotation.SystemControllerLog;
import com.winterarm.aspect.annotation.SystemServiceLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.Cacheable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * Created by huangdx on 2018/3/12.
 */
@Aspect
@Component
public class SystemLogAspect {

    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //Service层切点    
    @Pointcut("@annotation(com.winterarm.aspect.annotation.SystemServiceLog)")
    public void serviceAspect() {
    }

    //Controller层切点   可以基于类引用路径
    @Pointcut("@annotation(com.winterarm.aspect.annotation.SystemControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Around("controllerAspect()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Before Controller Invoke params{}", joinPoint.getArgs());
        Object o = joinPoint.proceed();
        logger.info("After Returning Controller return data {}", o);

    }

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("Before Controller Invoke params{}", joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "controllerAspect()", returning = "o")
    public void afterReturn(Object o) {
        logger.info("After Returning Controller return data {}", o);
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
    }


    @AfterReturning(pointcut = "serviceAspect()", returning = "o")
    public void doAfter(JoinPoint joinPoint, Object o) {
        logger.info("after the end of service invoke");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        logger.info("请求路径{}", request.getServletPath());
        //HttpSession session = request.getSession();
        //获取请求ip
        String ip = request.getRemoteAddr();
        logger.info("请求IP{}", ip);
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params += JSONObject.toJSONString(joinPoint.getArgs()[i]) + ";";
            }
        }
        //joinPoint.getStaticPart()
        logger.info("获取到的方法参数为{}", joinPoint.getArgs());
    }


    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getMthodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget()
                                     .getClass()
                                     .getName();
        String methodName = joinPoint.getSignature()
                                     .getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName()
                      .equals(methodName)) {
                method.getReturnType();
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    SystemServiceLog systemControllerLog = method.getAnnotation(SystemServiceLog.class);
                    if (null != systemControllerLog) {
                        description = systemControllerLog.description();
                    }
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget()
                                     .getClass()
                                     .getName();
        String methodName = joinPoint.getSignature()
                                     .getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName()
                      .equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    SystemControllerLog systemControllerLog = method.getAnnotation(SystemControllerLog.class);
                    if (null != systemControllerLog) {
                        description = systemControllerLog.description();
                    }
                    break;
                }
            }
        }
        return description;
    }
}
