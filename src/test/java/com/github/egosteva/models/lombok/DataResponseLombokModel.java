package com.github.egosteva.models.lombok;

import lombok.Data;

@Data
public class DataResponseLombokModel {
    public Integer id;
    public String email, first_name, last_name, avatar;
}
