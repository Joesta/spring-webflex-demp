package com.semanticcode.webfluxdemo.controller;

import com.semanticcode.webfluxdemo.dto.Response;
import com.semanticcode.webfluxdemo.dto.ResponseDto;
import com.semanticcode.webfluxdemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Joesta on 2021/05/01
 */

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {

    @Autowired
    private ReactiveMathService mathService;

    @GetMapping("square/{input}")
    public Mono<Response> findSquare(@PathVariable int input) {
        return this.mathService.findSquare(input);
    }

    @GetMapping(value = "table/{input}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTable(@PathVariable int input) {
        return this.mathService.multiplication(input);
    }

    @GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(@PathVariable int input) {
        return this.mathService.multiplication(input);
    }

    @PostMapping(value = "multiply")
    public Mono<Response> multiply(@RequestBody Mono<ResponseDto> responseDto) {
        return this.mathService.multiply(responseDto);
    }
}
