package com.thecommerce.app.common.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 메소드 실행시간 로깅
 * com.thecommerce.app 패키지 내의 모든 메소드 실행에 대해 측정
 * 제외: common.logger 패키지 내의 모든 클래스, common.exception 패키지 내의 모든 클래스
 */

@Slf4j
@Aspect
@Component
public class MethodExecutionTimeLogger {

    @Pointcut("execution(* com.thecommerce.app..*(..)) && " +
            "!execution(* com.thecommerce.app.common.logger..*(..)) && " +
            "!execution(* com.thecommerce.app.common.exception..*(..))")
    private void trackExecutionTime() {
    }

    @Around("trackExecutionTime()")
    public Object logExecutionTime(final ProceedingJoinPoint joinPoint) throws Throwable {

        // 메소드 실행시간 측정
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        long executionTimeMillis = endTime - startTime;

        // 클래스명, 메소드명 읽기
        Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();

        log.info("{}.{} took {}ms", className, methodName, executionTimeMillis);

        return result;
    }
}

