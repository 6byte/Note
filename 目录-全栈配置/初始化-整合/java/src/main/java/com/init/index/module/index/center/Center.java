package com.init.index.module.index.center;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class Center {
    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}
