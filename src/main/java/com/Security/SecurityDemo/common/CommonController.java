package com.Security.SecurityDemo.common;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CommonController {

    // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    // this methode is used to get the logged in user
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/me")
    public String getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Logged in user: " + authentication.getPrincipal().toString();
       // return "Logged in user: " + authentication.getName();
    }
    @GetMapping("/hello")
    public String hello() {

        System.out.println();
        return "Hollo Dear";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String getUser() {
        return "Hollo User";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String getAdmin() {
        return "Hollo Admin";
    }

}
