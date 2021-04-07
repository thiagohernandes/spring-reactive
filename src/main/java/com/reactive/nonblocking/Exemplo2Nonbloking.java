package com.reactive.nonblocking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;

@RestController
@RequestMapping("/nonblocking")
@Slf4j
public class Exemplo2Nonbloking {

    @GetMapping("/async-deferredresult")
    public DeferredResult<ResponseEntity<?>> handleReqDefResult(Model model) {
        log.info("Received async-deferredresult request");
        final DeferredResult<ResponseEntity<?>> output = new DeferredResult<>(4000L);

        ForkJoinPool.commonPool().submit(() -> {
            log.info("Processing in separate thread");
            try {
                log.info("Before....");
                Thread.sleep(6000);
                log.info("After....");
            } catch (InterruptedException e) {
            }
            output.setResult(ResponseEntity.ok("ok"));
        });

        log.info("servlet thread freed");
        return output;
    }
}
