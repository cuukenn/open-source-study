package com.cuukenn.openstudysource.caffeine.monitor.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author changgg
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {
    @Cacheable(cacheNames = "normal")
    @GetMapping(value = "/messages")
    public List<String> messages() {
        log.info("invoke messages api");
        return Arrays.asList("message-1", "message-2", "message-3");
    }
}
