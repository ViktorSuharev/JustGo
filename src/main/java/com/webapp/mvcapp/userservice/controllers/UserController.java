package com.webapp.mvcapp.userservice.controllers;

import com.webapp.mvcapp.userservice.service.UserService;
import com.webapp.mvcapp.userservice.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @RequestMapping("/register.html")
    public String index(Model model,
                        @RequestParam(value="login", required=true) String login,
                        @RequestParam(value="password", required=true) String password,
                        @RequestParam(value="name", required=true) String name,
                        @RequestParam(value="photo_id", required=true) long photoId) {
        UserService userService = new UserServiceImpl();

        model.addAttribute("status", userService.register(login, password, name, photoId));
        return "userservices/register";
    }

    @RequestMapping("/login.html")
    public String index(Model model,
                            @RequestParam(value="login", required=true) String login,
                        @RequestParam(value="password", required=true) String password) {
        UserService userService = new UserServiceImpl();
        model.addAttribute("status", userService.login(login, password));
        return "userservices/login";
    }

    @RequestMapping("/unregister.html")
    public String index(Model model,
        @RequestParam(value="login", required=true) String login) {
        UserService userService = new UserServiceImpl();
        model.addAttribute("status", userService.unregister(login));
        return "userservices/unregister";
    }
}
