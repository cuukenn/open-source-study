package io.github.cuukenn.opensource.study.sample.flowable.web;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author changgg
 */
@RestController
@RequestMapping("/api/task")
public class TaskFlowableController {
    private static final Logger log = LoggerFactory.getLogger(TaskFlowableController.class);
    @Resource
    private RuntimeService runtimeService;

    @Resource
    private HistoryService historyService;

    @GetMapping("/create")
    public String createTask() {
        log.info("[task-flowable][create-task]");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("MultiTask", UUID.randomUUID().toString());
        return String.format("processInstanceId:%s, businessKey:%s", processInstance.getId(), processInstance.getBusinessKey());
    }

    @GetMapping("/publish/{signal-name}")
    public String publishDataEvent(@PathVariable("signal-name") String name) {
        log.info("[task-flowable][publish-data-event][name: {}]", name);
        runtimeService.signalEventReceived(name);
        return "ok";
    }

    @GetMapping("/publish/1")
    public String publishDataEvent1() {
        return publishDataEvent("data1");
    }

    @GetMapping("/publish/2")
    public String publishDataEvent2() {
        return publishDataEvent("data2");
    }

    @GetMapping("/publish/3")
    public String publishDataEvent3() {
        return publishDataEvent("data3");
    }

    @GetMapping("/history")
    public List<HistoricActivityInstance> history() {
        // 查看历史
        return historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceEndTime().asc().list();
    }
}
