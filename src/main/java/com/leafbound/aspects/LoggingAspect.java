package com.leafbound.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*Source: https://mkyong.com/spring3/spring-aop-aspectj-annotation-example/ */

@Aspect
@Component
public class LoggingAspect {
    private static Logger log = Logger.getLogger(LoggingAspect.class);

    @Before(value = "execution(* com.leafbound.*.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Before advice for: [" + joinPoint.getTarget().getClass() + ":" + joinPoint.getSignature().getName()
                + "]");
    }

    // @After(value = "execution(*
    // com.leafbound.services.UserServiceImpl.getAllUserShows(..))")
    @After(value = "execution(* com.leafbound.*.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("After advice for: [" + joinPoint.getTarget().getClass() + ":" + joinPoint.getSignature().getName()
                + "]");
    }

    @Around(value = "execution(* com.leafbound.services.*.*(..))")
    public Object logDuring(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. get the arguments returned from the dao layer

        log.info("Around advice for: [" + joinPoint.getTarget().getClass() + ":" + joinPoint.getSignature().getName()
                + "]");

        Object[] args = joinPoint.getArgs();

        // 3. proceed the joinpoint
        Object result = joinPoint.proceed(args);
        log.info("Result of this method invoking is: " + result);

        // end the advice
        log.info("End of Around Advice");
        return result;
    }

}
