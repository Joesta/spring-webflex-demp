package com.semanticcode.webfluxdemo.controller;

import com.semanticcode.webfluxdemo.dto.Response;
import com.semanticcode.webfluxdemo.exception.InputValidationException;
import com.semanticcode.webfluxdemo.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Created by Joesta on 2021/05/06, 19:10
 */

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathValidationController {

    @Autowired
    private MathService mathService;

    @GetMapping("person/{name}")
    public String getString(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping( "square/{input}/throw")
    public Response findSquare(@PathVariable int input) {
        if (input < 10 || input > 20)
            throw new InputValidationException(input);
        return mathService.findSquare(input);
    }

    @GetMapping("sqrt/{input}/mono-error")
    public Mono<Response> monoError(@PathVariable int input) {
        return Mono.just(input)
                .handle((integer, sink) -> {
                    if (input >= 10 && input <= 20)
                        sink.next(integer);
                    else
                        sink.error(new InputValidationException(input));
                }).cast(Integer.class)
                .map(i -> this.mathService.findSquare(input));
    }

    @GetMapping("sqrt/{input}/monoErr")
    public Mono<ResponseEntity<Response>> assignment(@PathVariable int input) {
        return Mono.just(input)
                .filter(i -> i >= 10 && i <= 20)
                .map(i -> this.mathService.findSquare(i))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
