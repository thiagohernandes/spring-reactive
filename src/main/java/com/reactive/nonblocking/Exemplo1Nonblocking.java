package com.reactive.nonblocking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@RestController
@RequestMapping("/nonblocking")
@Slf4j
public class Exemplo1Nonblocking {

    @GetMapping()
    public ResponseEntity<Flux<List<Integer>>> getNumeros() throws Exception, InterruptedException {
        try {
            List<Integer> lista = new ArrayList<>();
            new Thread(() -> {
                log.info("Deferred task started");
                for (int x = 0; x < 10; x++) {
                    try {
                        Thread.sleep(1000L);
                        lista.add(x);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("Deferred task finished");
            }).start();
            return ResponseEntity.ok(Flux.just(lista));
        } catch (Exception e) {
            log.error("Problemas: {}", e);
            throw new Exception("Erro na chamada!!");
        }
    }

    @GetMapping("/test")
    public DeferredResult<String> getTestRequest() {
        log.info("Request processing started");
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        setResultInOtherThread(deferredResult);
        log.info("Request processing finished");
        return deferredResult;
    }

    private void setResultInOtherThread(DeferredResult<String> deferredResult) {
        new Thread(() -> {
            log.info("Deferred task started");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Deferred task finished");
            deferredResult.setResult("Test deferred result");
        }).start();
    }
}
