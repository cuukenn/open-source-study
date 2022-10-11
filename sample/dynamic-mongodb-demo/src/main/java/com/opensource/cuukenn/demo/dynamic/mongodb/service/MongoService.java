package com.opensource.cuukenn.demo.dynamic.mongodb.service;

import com.opensource.cuukenn.dynamic.database.mongodb.support.aop.DynamicMongo;
import org.springframework.aop.Advisor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@Service
public class MongoService implements IMongoService {
    private final MongoTemplate mongoTemplate;

    public MongoService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String test() {
        return mongoTemplate.exists(new Query(), "test001") + "";
    }

    @DynamicMongo("test-1")
    @Override
    public String test1() {
        Advisor s;
        return mongoTemplate.exists(new Query(), "test001") + "";
    }

    @DynamicMongo("test-2")
    @Override
    public String test2() {
        return mongoTemplate.exists(new Query(), "test001") + "";
    }

    @DynamicMongo("test-3")
    @Override
    public String test3() {
        return mongoTemplate.exists(new Query(), "test001") + "";
    }

    @DynamicMongo(instanceId = "test-2", databaseName = "test333")
    @Override
    public String test4() {
        return mongoTemplate.exists(new Query(), "test001") + "";
    }
}
