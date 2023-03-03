package com.example.demo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class UserController {

    @MessageMapping("user")
    public Flux<String> getUsers() {
        return Flux.fromStream(Stream.generate(() -> "user " + LocalDateTime.now()).limit(10))
                .delayElements(Duration.ofSeconds(1))
                .doOnEach(e -> System.out.println("sent"))
                .doFinally(r -> System.out.println("that's it"));
    }
}
