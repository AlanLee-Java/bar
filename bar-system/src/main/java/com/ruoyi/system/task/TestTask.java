package com.ruoyi.system.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {

    @Scheduled(cron = "0/30 * * * * ?")
    public void test() {
        System.out.println("每30秒执行一次");
    }

}
