package com.mglsk.demo.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.micrometer.common.util.StringUtils;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody()
    public String getMethodName(@RequestParam(value = "name", required = false) String name) {
        String greeting = "Hello ";

        if (StringUtils.isNotBlank(name)) {
            greeting += name;
        } else {
            greeting += "World";
        }

        return "<h1>" + greeting + "</h1>";
    }

}