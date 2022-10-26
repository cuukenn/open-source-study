package io.github.cuukenn.openstudysource.sample.flowable;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

/**
 * @author changgg
 */
@SpringBootTest
public class MultiTaskBpmnTest {
    private static final Logger log = LoggerFactory.getLogger(MultiTaskBpmnTest.class);
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Test
    public void task_bpmn_test() {
        // 发起任务
        ProcessInstance processInstance = runtimeService.startProcessInstanceById("MultiTask", UUID.randomUUID().toString());
        log.info("[task_bpmn_test][instanceId:{}]", processInstance.getId());
        // 查看历史
        List<HistoricActivityInstance> activities = historyService.createHistoricActivityInstanceQuery()
            .processInstanceId(processInstance.getId())
            .orderByHistoricActivityInstanceEndTime().asc()
            .list();
        for (HistoricActivityInstance activity : activities) {
            log.info("[task_bpmn_test][activityName:{}]", activity.getActivityName());
        }
    }

    @Test
    public void task_bpmn_history_test() {
        // 查看历史
        List<HistoricActivityInstance> activities = historyService.createHistoricActivityInstanceQuery()
            .orderByHistoricActivityInstanceEndTime().asc()
            .list();
        for (HistoricActivityInstance activity : activities) {
            log.info("[task_bpmn_test][activityName:{}]", activity.getActivityName());
        }
    }
}
