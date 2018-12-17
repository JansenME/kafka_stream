package nl.demo.kafka.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
@EnableAspectJAutoProxy
@Slf4j
public class PerformanceLogger {
    @Around("execution(* nl.demo.kafka.service.ProducerService.createProducer(..))")
    public Object aroundCreateProducer(ProceedingJoinPoint joinPoint){
        long start = System.currentTimeMillis();

        Integer numberOfAccounts = (Integer) joinPoint.getArgs()[0];

        try {
            joinPoint.proceed();

            String method = "ProducerService.createProducer()";
            String message = "Time it took to create a stream for " + numberOfAccounts + " accounts";

            logPerformance(start, method, message);
            return null;
        } catch (Throwable t) {
            log.error("Error during aroundCreateProducer", t);
        }

        return null;
    }

    private void logPerformance(long start, String method, String message) {
        log.info("Method: {}, Message: {}, Responsetime: {}", method, message, System.currentTimeMillis() - start);
    }
}
