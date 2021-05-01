package com.semanticcode.webfluxdemo.service;

import com.semanticcode.webfluxdemo.dto.Response;
import com.semanticcode.webfluxdemo.dto.ResponseDto;
import com.semanticcode.webfluxdemo.util.SleepUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Created by Joesta on 2021/05/01
 */

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input) {
        return Mono.fromSupplier(() -> input * input)
                .map(Response::new);
    }

    public Flux<Response> multiplication(int input) {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1)) // non-blocking sleep
                // .doOnNext(i -> SleepUtil.sleepSeconds(1))
                .doOnNext(i -> System.out.println("reactive-math-processing: " + i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(@RequestBody Mono<ResponseDto> dtoMono) {
        return dtoMono
                .map(dto ->  dto.getFirst() * dto.getSecond())
                .map(Response::new);
    }
}
