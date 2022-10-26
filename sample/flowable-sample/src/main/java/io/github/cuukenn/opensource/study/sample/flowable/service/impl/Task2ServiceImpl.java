package io.github.cuukenn.opensource.study.sample.flowable.service.impl;

import io.github.cuukenn.opensource.study.sample.flowable.service.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@Service
public class Task2ServiceImpl implements ITaskService {
    private static final Logger log = LoggerFactory.getLogger(Task2ServiceImpl.class);

    @Override
    public void invoke(String param) {
        log.info("[work][invoke 2 work]");
    }
}
