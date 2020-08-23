package com.macrosan.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/*
 * Spring AOP集成的AspectJ框架中通知执行顺序 
 * 1.目标方法被拦截,执行 @Around通知方法;
 * 2.执行目标方法之前,执行 @Before通知方法(如果有 @Before通知),环绕通知方法中目标方法之前的代码会先执行;
 * 3.执行目标方法;
 * 4.目标方法返回之前(return之前),如果是正常返回,执行 @AfterReturning通知方法,然后执行 @After通知方法;
 *   					          如果是抛出异常,执行 @AfterThrowing通知方法,然后执行 @After通知方法;
 * 5.目标方法返回,执行 @Around通知方法中剩余代码;
 */
@Aspect		//定义此类为切面类
@Slf4j
@Component
public class MethodQueryTimeAOP {
	@Pointcut("within(com.macrosan.service..*)")
	public void timePointCut() {}
	
	@Around("timePointCut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = jp.proceed();
		long end = System.currentTimeMillis();
		long time = end-start;
		log.info("Method Execute Time ==> "+time+"ms");
		return result;
	}
}
