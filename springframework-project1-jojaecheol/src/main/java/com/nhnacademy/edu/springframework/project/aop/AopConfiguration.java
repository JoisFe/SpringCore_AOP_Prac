package com.nhnacademy.edu.springframework.project.aop;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class AopConfiguration {
    CsvScores csvScores;
    CsvStudents csvStudents;

    private static final Log log = LogFactory.getLog(AopConfiguration.class);

    @Autowired
    public AopConfiguration(CsvScores csvScores,
                            CsvStudents csvStudents) {
        this.csvScores = csvScores;
        this.csvStudents = csvStudents;
    }

    @Before("execution(* com.nhnacademy.edu.springframework.project.repository.CsvScores.findAll(..))")
    private void catchNotScoresLoaded() {
        if (!csvScores.isLoaded()) {
            throw new IllegalStateException();
        }
    }

    @Before("execution(* com.nhnacademy.edu.springframework.project.repository.CsvStudents.findAll(..)) " +
        "|| execution(* com.nhnacademy.edu.springframework.project.repository.CsvStudents.merge(..))")
    private void catchNotStudentsLoaded() {
        if (!csvStudents.isLoaded()) {
            throw new IllegalStateException();
        }
    }

    @Around("execution(public * com.nhnacademy.edu.springframework.project.service.*.*())")
    public Object loggingTime(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch(pjp.getSignature().getName());

        try {
            stopWatch.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            log.info(stopWatch.prettyPrint());
        }
    }
}
