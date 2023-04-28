package com.github.egosteva.tests.models.pojo;

public class CreateUserBodyPojoModel {
    // String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
    String name, job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
