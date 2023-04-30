package com.github.egosteva.models.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataResponseLombokModel {
    public Integer id;
    public String email, avatar;
    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("last_name")
    public String lastName;
}
