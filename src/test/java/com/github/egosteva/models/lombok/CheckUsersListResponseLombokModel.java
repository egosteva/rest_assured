package com.github.egosteva.models.lombok;

import lombok.Data;

import java.util.List;

@Data
public class CheckUsersListResponseLombokModel {
    Integer page, per_page, total, total_pages;

    public List<DataResponseLombokModel> data;

    public SupportResponseLombokModel support;
}