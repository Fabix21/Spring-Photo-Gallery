package com.fhoszowski.photogallery.controllers;

import com.fhoszowski.photogallery.models.User;
import com.fhoszowski.photogallery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRESTController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/addUser")
    public String addUser( @RequestBody User newUser ) {
        userService.addUser(newUser);
        return "User has been successfully added!";
    }
}
