package com.brandon.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment environment;

    @GetMapping("/profile")
    public String profile() {


        List<String> profiles = Arrays.asList(environment.getActiveProfiles());
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");

        String defaultProfiles = profiles.isEmpty() ? "default" : profiles.get(0);
        System.out.println("==>"+realProfiles.contains("oauth"));
        return profiles.stream()
                .filter(p -> realProfiles.contains(p))
                .findAny()
                .orElse(defaultProfiles);
    }
}