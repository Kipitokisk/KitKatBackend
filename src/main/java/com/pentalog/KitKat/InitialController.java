package com.pentalog.KitKat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitialController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from my first controller.";
    }
}
