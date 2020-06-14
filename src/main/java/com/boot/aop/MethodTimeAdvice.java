package com.boot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.boot.common.util.LogUtil;

/**
 * 
 * @Title: LogAspect.java
 * @Package com.boot.aop
 * @ClassName: LogAspect
 * @Description: (这里描述用途)
 * @author shanchaoran
 * @date 2019年1月10日 下午4:16:39
 */
@Aspect
@Component
public class MethodTimeAdvice {
	
	public static long slowly_threshold = 300;
	/**
	 * 
	 * @Title: logPointcut
	 * @Description: (这里用一句话描述这个方法的作用) void (这里描述输出参数的作用)
	 * @throws
	 * @author shanchaoran
	 * @date 2019年1月10日 下午4:16:36
	 */
     @Pointcut("execution(* com.boot.web..*.*(..)) || execution(* com.boot.service..*.*(..))")
     public void logPointcut(){}
     @org.aspectj.lang.annotation.Around("logPointcut()")

     /**
      * 
      * @Title: doAround
      * @Description: (这里用一句话描述这个方法的作用)
      * @param joinPoint
      * @return
      * @throws Throwable Object (这里描述输出参数的作用)
      * @throws
      * @author shanchaoran
      * @date 2019年1月10日 下午4:16:50
      */
     public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
//    	 LOG.debug("logPointcut " + joinPoint + "\t");
 		long start = System.currentTimeMillis();
 		try {
 			Object result = joinPoint.proceed();
 			String methodName = joinPoint.toString();
 			
 			long end = System.currentTimeMillis();
 			long runTime = end - start;
 			if(runTime >= slowly_threshold){
 				LogUtil.info("call Method runs too slowly" + methodName + " : time : " + runTime + " ms!");
 			}
 			return result; 

 		} catch (Throwable e) {
 			
 			String methodName = joinPoint.toString();
 			long end = System.currentTimeMillis();
 			long runTime = end - start;
 			if(runTime >= slowly_threshold){
 				LogUtil.info("call Method runs too slowly" + methodName + " : time : " + runTime + " ms!");
 			}
 			throw e;
 		}

     }
}