package org.hr.ad.test;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 描述：activiti测试
 *
 * @author nijd
 * @create 2020-06-22 3:23 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ActivitiTest {

    @Autowired
    private ProcessEngine processEngine;

//    @Autowired
//    private RepositoryService repositoryService;
//
//    @Autowired
//    private RuntimeService runtimeService;
//
//    @Autowired
//    private HistoryService historyService;

    /**
     * 部署流程
     */
    @Test
    public  void deployProcess(){
       Deployment deployment = processEngine.getRepositoryService().createDeployment()
               .name("流程测试")
               .addClasspathResource("processes/test.bpmn")
               .deploy();
       log.info("部署成功----id:{};名称:{}",deployment.getId(),deployment.getName());
    }

    /**
     * 查询所有流程
     */
    @Test
    public void showProcess(){
        List<ProcessDefinition> processList = processEngine.getRepositoryService().createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc()
                .list();
        for(ProcessDefinition pd : processList){
            System.out.println("流程定义ID:"+pd.getId());
            System.out.println("流程定义的名称:"+pd.getName());
            System.out.println("流程定义的key:"+pd.getKey());
            System.out.println("流程定义的版本:"+pd.getVersion());
            System.out.println("资源名称bpmn文件:"+pd.getResourceName());
            System.out.println("资源名称png文件:"+pd.getDiagramResourceName());
            System.out.println("部署对象ID："+pd.getDeploymentId());
            System.out.println("*********************************************");
        }
    }

    /**
     * 启动流程demo
     */
    @Test
    public void startProcess(){
        String key ="demo";
        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(key);
        log.info("启动成功----id:{};名称:{}",pi.getId(),pi.getProcessDefinitionId());
    }

    /**
     * 获取流程实例当前的环节
     */
    @Test
    public void getProcessActive(){
        String processInstanceId = "30001";
        ProcessInstance pi = processEngine.getRuntimeService().createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        log.info("流程节点:{}",pi == null ? "流程已结束" : pi.getActivityId());
    }

    /**
     * 完成环节任务
     */
    @Test
    public void completeTask(){
        String taskId  = "30004";
        processEngine.getTaskService().complete(taskId);
        log.info("任务完成,任务id:{}",taskId);
    }

}
