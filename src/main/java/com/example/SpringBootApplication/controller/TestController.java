package com.example.SpringBootApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home()
    {
        String str
                = "<html><body><font color=\"green\">"
                + "<h1>WELCOME To GeeksForGeeks</h1>"
                + "</font></body></html>";
        return str;
    }

    // Another syntax to implement a
    // GET method
    @RequestMapping(
            method = { RequestMethod.GET },
            value = { "/gfg" })

    public String info()
    {
        return getString();
    }

    private String getString() {

        return  "<html><body><font color=\"green\">"
                + "<h2>GeeksForGeeks is a Computer"
                + " Science portal for Geeks. "
                + "This portal has been "
                + "created to provide well written, "
                + "well thought and well explained "
                + "solutions for selected questions."
                + "</h2></font></body></html>";
    }
}
