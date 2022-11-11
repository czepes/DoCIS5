package ru.sfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sfu.entity.User;
import ru.sfu.repository.RoleRepository;
import ru.sfu.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Registration controller
 * @author Agapchenko V.V.
 */
@Controller
public class RegisterController {

    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public RegisterController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    /**
     * Register page handler
     * @return 'Register' view path
     */
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "register";
    }

    /**
     * Registration handler
     * @return 'Register' view path if registration failed
     */
    @PostMapping("/register")
    public String saveUser(
            @ModelAttribute("user") @Valid User user,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            return "register";
        }

        if (!userService.saveUser(user)) {
            model.addAttribute(
                    "userExists",
                    "User '" + user.getUsername() + "' already exists!"
            );
            model.addAttribute("roles", roleRepository.findAll());
            return "register";
        }

        return "redirect:/login";
    }
}
