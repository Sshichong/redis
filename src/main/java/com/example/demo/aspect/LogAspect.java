package com.example.demo.aspect;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.DemoLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;



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
     * 后置返回通知
     * 这里需要注意的是:
     *      如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
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
        LOGGER.info("==============================RETURNINGEND==============================");
    }

    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *      对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "log()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        //方法名
        LOGGER.info("CLASS METHOD:"+joinPoint.getSignature().getDeclaringType()+"."+joinPoint.getSignature().getName());
        if (exception instanceof NullPointerException){
            LOGGER.info("发生空指针异常");
        }
    }

    /**
     * 环绕通知：
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     * @param proceedingJoinPoint
     * @return
     */
    @Around("execution(* com.example.demo.controller..*.selectOneById*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        LOGGER.info("环绕通知目标方法名："+proceedingJoinPoint.getSignature().getName());
        try {
            Object obj = proceedingJoinPoint.proceed();
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;

    }


    /**
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
     * @param joinPoint
     */
    @After("log()")
    public void doAfterAdvice(JoinPoint joinPoint){

        System.out.println("后置通知执行了!!!!");
    }


}
