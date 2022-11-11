package ru.sfu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Login controller
 * @author Agapchenko V.V.
 */
@Controller
public class LoginController {

    /**
     * Login page handler
     * @return 'Login' view path
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
