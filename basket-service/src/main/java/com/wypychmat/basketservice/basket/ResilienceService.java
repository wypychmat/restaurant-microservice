package com.wypychmat.basketservice.basket;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Supplier;

public class ResilienceService {


    private final Logger log;
    private final String serviceName;

    public ResilienceService(Logger log, String serviceName) {
        this.log = log;
        this.serviceName = serviceName;
    }

    public <T> T doWithSupplier(Supplier<T> supplier, Function<Throwable, T> fallback) {
        return Decorators.ofSupplier(supplier)
                .withRetry(retry())
                .withCircuitBreaker(circuitBreaker())
                .withFallback(fallback)
                .get();
    }

    private Retry retry() {
        Retry retry = Retry.of(serviceName, RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(200))
                .build());
        retry.getEventPublisher()
                .onEvent(event -> log.info("Retry event: {}", event));

        return retry;
    }


    private CircuitBreaker circuitBreaker() {
        CircuitBreaker circuitBreaker = CircuitBreaker.of(serviceName, CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .minimumNumberOfCalls(3)
                .slidingWindowSize(3)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .permittedNumberOfCallsInHalfOpenState(1)
                .build());

        circuitBreaker.getEventPublisher()
                .onEvent(event -> log.info("Circuit Breaker event: {}", event));

        return circuitBreaker;
    }

}
