package org.hr.ad;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 描述：启动
 *
 * @author nijd
 * @create 2020-06-22 1:58 下午
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivitiDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivitiDemoApplication.class, args);
    }
}
