package com.github.egosteva.tests.models.lombok;

import lombok.Data;

@Data
public class CreateUserResponseLombokModel {
    // {
    //    "name": "morpheus",
    //    "job": "leader",
    //    "id": "796",
    //    "createdAt": "2023-04-28T18:39:42.165Z"
    //}

    String name, job, id,createdAt;
  }
