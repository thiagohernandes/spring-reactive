package com.reactive.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

@RestController
@RequestMapping("/webflux")
@Slf4j
public class Exemplo1Webflux {

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> findAll() {
        return Flux.fromIterable(Arrays.asList(1,2,3,4)).delayElements(Duration.ofMillis(500));
    }

}
