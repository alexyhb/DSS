package com.dao;

import com.pojo.company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface companyDao {
    company getCompanyByid(int id);
    List<company> getCompanyList(@Param(value = "industry")String industry);
    List<company> getLoginList(@Param(value = "username")String username);
    boolean addCompany(@Param(value = "industry")String industry, @Param(value = "nationality")String nationality, @Param( "quantity")int quantity, @Param ("username")String username, @Param( "password")String password, @Param("name")String name, @Param("scale")int scale);
    int idOfCompany(String username);
    List<company> getList();

}
