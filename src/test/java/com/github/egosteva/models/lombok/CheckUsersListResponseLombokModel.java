package com.github.egosteva.models.lombok;

import lombok.Data;

import java.util.List;

@Data
public class CheckUsersListResponseLombokModel {
    Integer page, per_page, total, total_pages;

    public List<DataResponseLombokModel> data;

  //  public static class DataResponseLombokModel {
    //    public Integer id;
      //  public String email, first_name, last_name, avatar;
   // }

    public SupportResponseLombokModel support;

  //  public static class SupportResponseLombokModel {
 //       public String url, text;
   // }
}