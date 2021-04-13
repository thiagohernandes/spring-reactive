package com.reactive.callable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Supplier;

@RestController
@RequestMapping("/callable")
@Slf4j
public class Exemplo1Callable {

    @GetMapping("/future")
    public CompletableFuture<Map<String, Object>> simpleAsync(@RequestParam(defaultValue="5") long t) throws InterruptedException {
        Map<String, Object> obj = new HashMap<>();
        Thread.sleep(t * 1000);
        obj.put("key", "success");
        return CompletableFuture.completedFuture(obj);
    }

    @GetMapping(value = "/future2")
    public Future<String> simpleAsync2(@RequestParam(defaultValue="5") long t)
            throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Callable<String> task = () -> buildCallable();
        Future<String> future = executor.submit(task);
        return CompletableFuture.completedFuture(future.get());
    }

    @GetMapping(value = "/future3")
    public Future<String> simpleAsync3(@RequestParam(defaultValue="5") long t)
            throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Supplier<String> task = () -> {
            try {
                return buildCallable();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        };
        return CompletableFuture.supplyAsync(task, service);
    }

    @GetMapping(value = "/future4")
    public Future<String> simpleAsync4(@RequestParam(defaultValue="5") long t)
            throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Callable<String> task = () -> buildCallable();
        Future<String> future = executor.submit(task);
            return CompletableFuture.completedFuture(future.get());
    }

    private String buildCallable() throws InterruptedException {
        Thread.sleep(5000L);
        return "Nice Callable";
    }

}
