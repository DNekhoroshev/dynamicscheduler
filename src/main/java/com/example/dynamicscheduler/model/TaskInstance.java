package com.example.dynamicscheduler.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TaskInstance implements Runnable {

    private final TaskDefinition taskDefinition;

    @Override
    public void run() {
        System.out.println("Running action: " + taskDefinition.getName());
        System.out.println("With Data: " + taskDefinition.getHttpTarget().getUrl());
    }
}
