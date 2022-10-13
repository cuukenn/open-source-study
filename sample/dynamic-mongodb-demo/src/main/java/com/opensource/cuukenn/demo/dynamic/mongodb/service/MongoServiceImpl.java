package com.opensource.cuukenn.demo.dynamic.mongodb.service;

import com.opensource.cuukenn.dynamic.database.mongodb.support.aop.DynamicMongo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author changgg
 */
@Service
@DynamicMongo("test-1")
@EnableTransactionManagement
public class MongoServiceImpl implements IMongoService {
    private final MongoTemplate mongoTemplate;

    public MongoServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String test() {
        return mongoTemplate.exists(new Query(), "test001") + "";
    }

    @DynamicMongo("test-1")
    @Override
    public String test1() {
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String test5() {
        return mongoTemplate.exists(new Query(), "test001") + "";
    }
}
