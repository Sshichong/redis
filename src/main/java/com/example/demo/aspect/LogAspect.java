package com.example.demo.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.DemoLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by shichong on 2018/7/2.
 * 通知类型有：前置通知、后置最终通知、后置返回通知、后置异常通知、环绕通知
 * JoinPoint对象封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,就可以获取到封装了该方法信息的JoinPoint对象.
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoLog.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();


   // @Pointcut("execution(public * com.example.demo.controller.IllnessController.*(..))")
   @Pointcut("@annotation(com.example.demo.annotation.DemoLog)")  //定义切入点
    public void log(){}

    /**
     * 在方法执行之前执行
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
//        System.out.printf("========================doBefore=====================");
//        System.out.println("\n");
       /* //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        Object obj1 = joinPoint.getTarget();
        System.out.println(obj1);
        System.out.println(obj);
        //aop代理类的信息
        joinPoint.getThis();
        //代理的目标对象
        joinPoint.getTarget();
        //用的最多，通知的签名
        Signature signature =joinPoint.getSignature();
        //代理的是哪一个方法
        System.out.println(signature.getName());
        LOGGER.info("代理的是哪一个方法："+signature.getName());
        //aop代理类的类（class）信息
        signature.getDeclaringType();
        //获取RequestAttrubutes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request =(HttpServletRequest)requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //如果是session信息的话可以这样写
       //HttpServletRequest request =(HttpServletRequest)requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        Enumeration<String> enumeration =request.getParameterNames();
        Map<String,String> parameterMap = new HashMap<String,String>();
        while (enumeration.hasMoreElements()){
            String parameter =enumeration.nextElement();
            parameterMap.put(parameter,request.getParameter(parameter));
        }
        String str = JSON.toJSONString(parameterMap);
        if(obj.length>0){
            System.out.println(str);
            LOGGER.info("请求参数为："+str);
        }*/
       startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes =(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //切入点获取value
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DemoLog demoLog = method.getAnnotation(DemoLog.class);
        LOGGER.info("========================doBefore=====================");
        LOGGER.info("LOG_DESCRIPTION:"+demoLog.value());
        LOGGER.info("LOG_MODULE:"+demoLog.module());
        LOGGER.info("LOG_OPERATE:"+demoLog.operate());
        LOGGER.info("IP："+request.getRemoteAddr());
        LOGGER.info("ARGS："+ Arrays.toString(joinPoint.getArgs())); //请求信息
        LOGGER.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

    }

    /**
     * 在方法执行之后执行
     */
    @After("log()")
    public void doAfter(){
//        System.out.printf("========================doAfter=======================");
//        System.out.println("\n");
        LOGGER.info("========================doAfter=======================");
    }

    /**
     * 结果返回
     * @param object
     * @throws Throwable
     */
    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object) throws Throwable{
        //处理完请求，返回内容
        String json = JSONObject.toJSONString(object);
        LOGGER.info("PESPONSE:\n"+json);
        LOGGER.info("SPEND TIME:"+(System.currentTimeMillis()-startTime.get())+"ms");
    }


}
