package com.mritun.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/query")
public class QueryController {
    @GetMapping
    public String getQuery() {
        return "Good query!";
    }
}
