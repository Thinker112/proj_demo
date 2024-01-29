//package com.example.demo.webflux.controller;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.codec.ServerSentEvent;
//import org.springframework.http.codec.multipart.FilePart;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.reactive.result.view.Rendering;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebSession;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//
////@CrossOrigin
//@RestController
//@RequestMapping("/webflux")
//public class WebFluxCtrl {
//    @RequestMapping("/hello")
//    public String hello() {
//        return "Hello WebFlux";
//    }
//
//    @RequestMapping("/Mono")
//    public Mono<String> testMono() {
//        return Mono.just("hello Mono");
//    }
//
//    @RequestMapping("/Flux")
//    public Flux<String> testFlux() {
//        return Flux.just("hello1", "hello2");
//    }
//
//    @RequestMapping(value = "/sse", produces = "text/event-stream")
//    public Flux<ServerSentEvent<String>> testSSE() {
//        return Flux.range(1, 100)
//                .map(i -> ServerSentEvent.builder("sse-" + i)
//                        .id(i.toString())
//                        .comment("comment-" + i)
//                        .event("event-" + i)
//                        .build())
//                .delayElements(Duration.ofMillis(400));
//    }
//
//    //WebFlux： 向下兼容原来SpringMVC的大多数注解和API；
//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "key",required = false,defaultValue = "哈哈") String key,
//                        ServerWebExchange exchange,
//                        WebSession webSession,
//                        HttpMethod method,
//                        HttpEntity<String> entity,
//                        @RequestBody String s,
//                        FilePart file){
//
////        file.transferTo() //零拷贝技术；
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        String name = method.name();
//
//
//
//        Object aaa = webSession.getAttribute("aaa");
//        webSession.getAttributes().put("aa","nn");
//
//        return "Hello World!!! key="+key;
//    }
//
//
//
//    // Rendering：一种视图对象。
//    @GetMapping("/bai")
//    public Rendering render(){
////        Rendering.redirectTo("/aaa"); //重定向到当前项目根路径下的 aaa
//        return   Rendering.redirectTo("http://www.baidu.com").build();
//    }
//
//    @GetMapping("/haha")
//    public Mono<String> haha(){
//
////        ResponseEntity.status(305)
////                .header("aaa","bbb")
////                .contentType(MediaType.APPLICATION_CBOR)
////                .body("aaaa")
////                .
//
//        return Mono.just(0)
//                .map(i-> 10/i)
//                .map(i->"哈哈-"+i);
//    }
//
//}
