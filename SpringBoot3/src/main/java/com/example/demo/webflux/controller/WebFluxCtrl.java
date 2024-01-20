package com.example.demo.webflux.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@CrossOrigin
@RestController
@RequestMapping("/webflux")
public class WebFluxCtrl {


    @RequestMapping("/hello")
    public String hello() {
        return "Hello WebFlux";
    }

    @RequestMapping("/Mono")
    public Mono<String> testMono() {
        return Mono.just("hello Mono");
    }

    @RequestMapping("/Flux")
    public Flux<String> testFlux() {
        return Flux.just("hello1", "hello2");
    }

    @RequestMapping(value = "/sse", produces = "text/event-stream")
    public Flux<ServerSentEvent<String>> testSSE() {
        return Flux.range(1, 100)
                .map(i -> ServerSentEvent.builder("sse-" + i)
                        .id(i.toString())
                        .comment("comment-" + i)
                        .event("event-" + i)
                        .build())
                .delayElements(Duration.ofMillis(400));
    }

}
