package com.example.dynamicscheduler.sevice;

import com.example.dynamicscheduler.model.TaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
public class TaskSchedulingService {

    @Autowired
    private TaskScheduler taskScheduler;

    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public void scheduleATask(String jobId, TaskInstance tasklet) {
        System.out.println("Scheduling task with job id: " + jobId + " and cron expression: " + tasklet.getTaskDefinition().getSchedule());
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(
                tasklet,
                new CronTrigger(
                        tasklet.getTaskDefinition().getSchedule(),
                        TimeZone.getTimeZone(TimeZone.getDefault().getID())
                )
        );
        jobsMap.put(jobId, scheduledTask);
    }

    public void removeScheduledTask(String jobId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if(scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }
}
