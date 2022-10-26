package io.github.cuukenn.openstudysource.sample.flowable.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author changgg
 */
public interface ITaskService extends JavaDelegate {
    /**
     * 执行任务
     *
     * @param param 参数
     */
    void invoke(String param);

    /**
     * 执行任务
     *
     * @param delegateExecution 执行器
     */
    @Override
    default void execute(DelegateExecution delegateExecution) {
        invoke(delegateExecution.getVariable("workId", String.class));
    }
}
