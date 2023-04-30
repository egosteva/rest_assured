package com.github.egosteva.models.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CheckUsersListResponseLombokModel {
    Integer page,total;
    @JsonProperty("per_page")
    Integer perPage;
    @JsonProperty("total_pages")
    Integer totalPages;

    public List<DataResponseLombokModel> data;

    public SupportResponseLombokModel support;
}