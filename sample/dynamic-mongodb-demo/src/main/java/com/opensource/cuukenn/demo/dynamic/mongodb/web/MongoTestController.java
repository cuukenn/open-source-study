package com.opensource.cuukenn.demo.dynamic.mongodb.web;

import com.opensource.cuukenn.demo.dynamic.mongodb.service.IMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changgg
 */
@RestController
@RequestMapping("/api/mongo")
public class MongoTestController {
    private IMongoService mongoService;
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoService(IMongoService mongoService) {
        this.mongoService = mongoService;
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("test")
    public String testZero() {
        return mongoTemplate.exists(new Query(), "testZero") + "";
    }

    @GetMapping("test-0")
    public String test() {
        return mongoService.test();
    }

    @GetMapping("test-1")
    public String test1() {
        return mongoService.test1();
    }

    @GetMapping("test-2")
    public String test2() {
        return mongoService.test2();
    }

    @GetMapping("test-3")
    public String test3() {
        return mongoService.test3();
    }

    @GetMapping("test-4")
    public String test4() {
        return mongoService.test4();
    }

    @GetMapping("test-5")
    public String test5() {
        return mongoService.test5();
    }
}
