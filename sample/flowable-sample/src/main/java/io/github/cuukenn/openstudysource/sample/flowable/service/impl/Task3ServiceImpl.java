package io.github.cuukenn.openstudysource.sample.flowable.service.impl;

import io.github.cuukenn.openstudysource.sample.flowable.service.ITaskService;
import io.github.cuukenn.openstudysource.sample.flowable.service.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@Service
public class Task3ServiceImpl implements ITaskService {
    private static final Logger log = LoggerFactory.getLogger(Task3ServiceImpl.class);

    @Override
    public void invoke(String param) {
        log.info("[work][invoke 3 work]");
    }
}
