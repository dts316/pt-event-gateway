package com.philantree.eventgateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/default")
public class DefaultController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String greeting(){
        return "hello";
    }
}
