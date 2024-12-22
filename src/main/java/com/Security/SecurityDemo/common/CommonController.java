package com.Security.SecurityDemo.common;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CommonController {

    @GetMapping("/hello")
    public String hello() {
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
