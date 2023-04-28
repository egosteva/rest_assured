package com.github.egosteva.tests.models.lombok;

public class CreateUserResponseLombokModel {
    // {
    //    "name": "morpheus",
    //    "job": "leader",
    //    "id": "796",
    //    "createdAt": "2023-04-28T18:39:42.165Z"
    //}

    String name, job, id,createdAt;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
