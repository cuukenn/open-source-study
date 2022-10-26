package io.github.cuukenn.opensource.study.sample.flowable.service.impl;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author changgg
 */
public class EndTaskService implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(EndTaskService.class);

    @Override
    public void execute(DelegateExecution execution) {
        log.info("[task][end task]");
    }
}
