package io.github.cuukenn.openstudysource.sample.flowable.service.impl;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author changgg
 */
public class StartTaskService implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(EndTaskService.class);

    @Override
    public void execute(DelegateExecution execution) {
        log.info("[task][start task]");
    }
}
