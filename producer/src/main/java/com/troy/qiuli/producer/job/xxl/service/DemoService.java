package com.troy.qiuli.producer.job.xxl.service;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author caoqiang
 * @date 2020-09-03 17:04
 * @desc
 */
@Component
public class DemoService {


    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");

        for (int i = 0; i < 5; i++) {
            XxlJobLogger.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println("================test================");
        return ReturnT.SUCCESS;
    }

}