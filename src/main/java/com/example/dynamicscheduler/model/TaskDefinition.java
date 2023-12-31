package com.example.dynamicscheduler.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
public class TaskDefinition {

    private String name;
    private String description;
    private String schedule;
    private HttpTarget httpTarget;
    private boolean onlyOneInstance;

    @Data
    public static class HttpTarget {
        private String url;
        private String method;
        private Map<String, String> headers;
        private String body;
    }

}
