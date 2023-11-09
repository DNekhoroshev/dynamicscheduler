package com.example.dynamicscheduler.rest;

import com.example.dynamicscheduler.model.TaskDefinition;
import com.example.dynamicscheduler.model.TaskInstance;
import com.example.dynamicscheduler.sevice.TaskSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/schedule")
public class JobSchedulingController {

    @Autowired
    private TaskSchedulingService taskSchedulingService;

    @PostMapping(path="/create-task", consumes = "application/json", produces="application/json")
    public String scheduleATask(@RequestBody TaskDefinition taskDefinition) {
        TaskInstance task = new TaskInstance(taskDefinition);
        String taskId = UUID.randomUUID().toString();
        taskSchedulingService.scheduleATask(taskId, task);
        return String.format("{ \"ID\" : \"%s\"}", taskId);
    }

    @GetMapping(path="/stop-task/{jobid}")
    public void removeJob(@PathVariable String jobid) {
        taskSchedulingService.removeScheduledTask(jobid);
    }
}
