package com.ldw.timeSchedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/22.
 * 在applicatioContext.xml中配置定时器的实现方式，注解/配置方式
 * 如注解实现则在定时任务上增加@Scheduled（cron=‘表达式’）
 *
 */
@Component("timerDo")
public class TimeSchedule {
    int i = 0;

    @Scheduled(cron = "0/5 * * * * ?")
    public void schedule() {
        System.out.println("这是一个定时任务，现在时间是ss"
                + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .format(new Date()));
        i++;
    }
}
