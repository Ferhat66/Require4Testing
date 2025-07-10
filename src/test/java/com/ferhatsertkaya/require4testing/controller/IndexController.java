package com.ferhatsertkaya.require4testing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index"; // verweist auf src/main/resources/templates/index.html
    }
}