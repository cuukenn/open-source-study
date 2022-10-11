package com.opensource.cuukenn.app.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author changgg
 */
@RestController
@RequestMapping("/sse")
public class SseController {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping(value = "/date", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String getCurrentDate() {
        return "data:" + LocalDateTime.now().format(formatter) + "\n\n";
    }
}
