package com.example.service.controller;

import com.example.common.GreetingUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return GreetingUtil.greet(name);
    }
}
