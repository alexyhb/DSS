package com.service;

import com.alibaba.fastjson.JSONObject;
import com.pojo.company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface companyService {
    JSONObject Login(String username, String password);
    List<company> getCompanyList(String industry);
    boolean addCompany(@Param(value = "industry")String industry, @Param(value = "nationality")String nationality, @Param( "quantity")int quantity, @Param ("username")String username, @Param( "password")String password, @Param("name")String name, @Param("scale")int scale);
    int idOfCompany(String name);
    company getCompanyByid(int id);
}
