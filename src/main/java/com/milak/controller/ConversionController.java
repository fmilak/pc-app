package com.milak.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController {

    @RequestMapping("convert")
    public String convert() {
        return "Convert REST works";
    }

}
