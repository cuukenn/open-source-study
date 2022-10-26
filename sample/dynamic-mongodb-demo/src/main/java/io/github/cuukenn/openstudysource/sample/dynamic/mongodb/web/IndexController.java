package io.github.cuukenn.openstudysource.sample.dynamic.mongodb.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changgg
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "hello world";
    }
}
