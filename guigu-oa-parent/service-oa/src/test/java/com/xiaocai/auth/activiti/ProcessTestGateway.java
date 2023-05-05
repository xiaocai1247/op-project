package com.xiaocai.auth.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ProcessTestGateway {
    //注入RepositoryService
    @Autowired
    private RepositoryService repositoryService;

    //注入RuntimeService
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    //1 部署流程定义
    @Test
    public void deployProcess() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("process/qingjia003.bpmn20.xml")
                .name("请假申请流程002")
                .deploy();
        System.out.println("部署流程id为：" + deployment.getId());
        System.out.println("部署流程名字为：" + deployment.getName());
    }

    //启动流程实例
    @Test
    public void startProcessInstance() {
        Map<String, Object> map = new HashMap<>();
        //设置请假天数
        map.put("day", "3");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("qingjia003");
        System.out.println("流程定义id：" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例id：" + processInstance.getId());
    }

    //查询个人的代办任务---zhao6
    @Test
    public void findTaskList() {
//        String assignee = "wang5";
//        String assignee = "gouwa";
        String assignee = "xiaoli";
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assignee).list();
        for (Task task : list) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }
    }

    //完成任务
    @Test
    public void completTask() {
        Task task = taskService.createTaskQuery()
                .taskAssignee("wang5")  //要查询的负责人
                .singleResult();//返回一条

        //完成任务,参数：任务id
        taskService.complete(task.getId());
    }


}
