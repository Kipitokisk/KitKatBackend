package com.pentalog.KitKat.Controller;

import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public User saveUser(@Valid @RequestBody User user){
        return this.userService.saveUser(user);
    }

}
