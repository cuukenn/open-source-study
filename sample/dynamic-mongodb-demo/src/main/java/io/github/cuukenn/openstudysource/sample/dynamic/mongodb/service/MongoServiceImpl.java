package io.github.cuukenn.openstudysource.sample.dynamic.mongodb.service;

import io.github.cuukenn.dynamic.database.mongodb.support.DynamicMongo;
import io.github.cuukenn.dynamic.database.mongodb.support.context.parser.HeaderValueParser;
import io.github.cuukenn.dynamic.database.mongodb.support.context.parser.SessionValueParser;
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

    @Override
    @DynamicMongo(instanceId = "#{#instanceId}")
    public String test6(String instanceId) {
        return mongoTemplate.exists(new Query(), "test006") + "";
    }


    @Override
    @DynamicMongo(HeaderValueParser.HEADER_PREFIX + "instanceId")
    public String test7() {
        return mongoTemplate.exists(new Query(), "test007") + "";
    }

    @Override
    @DynamicMongo(SessionValueParser.SESSION_PREFIX + "instanceId")
    public String test8() {
        return mongoTemplate.exists(new Query(), "test008") + "";
    }
}
