package ru.sfu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home controller
 * @author Agapchenko V.V.
 */
@Controller
public class HomeController {
    /**
     * Home page handler
     * @return 'Index' view path
     */
    @GetMapping
    public String welcome() {
        return "index";
    }
}
