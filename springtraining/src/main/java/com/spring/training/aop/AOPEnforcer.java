package com.spring.training.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPEnforcer {
	@Pointcut("execution(* com.spring.training.aop.Callee.hello(..)) && args(str)")
	public void method(String str) {

	}

	@Before("method(str)")
	public void beforeMethod(JoinPoint jp,
	                         String str) {
		System.out.println(" jp : "
		                   + jp.toLongString());
		System.out.println("Before Callee : "
		                   + str);
	}

	@After("method(str)")
	public void afterMethod(JoinPoint jp,
	                        String str) {
		System.out.println("After Callee : "
		                   + str);

	}

	@AfterReturning(value = "method(str)",
	                returning = "retVal")
	public void afterReturn(JoinPoint jp,
	                        String str,
	                        String retVal) {
		System.out.println("After Callee : "
		                   + str
		                   + " Return value : "
		                   + retVal);

	}

	@AfterThrowing(value = "method(str)",
	               throwing = "exp")
	public void afterThrowing(JoinPoint jp,
	                          String str,
	                          Exception exp) {
		System.out.println("After Callee : "
		                   + str
		                   + " Throwing value : "
		                   + exp);

	}
	
	
	@Around(value = "method(str)")
	public Object around(ProceedingJoinPoint pjp,
	                          String str) {
		try {
			long nanoTime = System.nanoTime();
			
			Object[] args = pjp.getArgs();
			args[0] = "mehmet";
			
			Object proceed = pjp.proceed(args);
			
			nanoTime = System.nanoTime() - nanoTime;
			System.out.println("Delta Call : " + nanoTime);
			
			String retVal = (String)proceed;
			retVal += " intercepted.";
			return retVal;
		} catch (Throwable e) {
			return null;
		}
	}

	@Around("within(com.spring.training.aop.Callee) && @annotation(logAnno)")
	public Object around2(ProceedingJoinPoint pjp,
	                          MyLogAnno logAnno) {
		try {
			
			long nanoTime = System.nanoTime();
			Object proceed = pjp.proceed();
			nanoTime = System.nanoTime() - nanoTime;

			Class<?> declaringType = pjp.getSignature().getDeclaringType();
			Logger logger = LoggerFactory.getLogger(declaringType);
			if (logAnno.type().equals("DEBUG")) {
				logger.debug("Delta Call :" + nanoTime);
			} else {
				logger.info("Delta Call :" + nanoTime);
			}
		
			return proceed;
		} catch (Throwable e) {
			return null;
		}
	}

}
