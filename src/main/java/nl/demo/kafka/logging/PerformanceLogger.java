package nl.demo.kafka.logging;

import lombok.extern.slf4j.Slf4j;
import nl.demo.kafka.model.Account;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

@Aspect
@Configuration
@EnableAspectJAutoProxy
@Slf4j
public class PerformanceLogger {
    @Around("execution(* nl.demo.kafka.service.ProducerService.createStream(..))")
    public List<Account> aroundCreateStream(ProceedingJoinPoint joinPoint){
        long start = System.currentTimeMillis();

        Integer numberOfAccounts = (Integer) joinPoint.getArgs()[0];

        try {
            List<Account> response = (List<Account>) joinPoint.proceed();

            String method = "ProducerService.createStream()";
            String message = "Time it took to create a stream for " + numberOfAccounts + " accounts";

            logPerformance(start, method, message);
            return response;
        } catch (Throwable t) {
            log.error("Error during aroundCreateStream", t);
        }

        return null;
    }

    private void logPerformance(long start, String method, String message) {
        log.info("Method: {}, Message: {}, Responsetime: {}", method, message, System.currentTimeMillis() - start);
    }
}
