package com.mainul35;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootRestController {

    @GetMapping("/")
    public String test() {
        return "Test passed";
    }
}
