package com.dao;

import com.pojo.customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface customerDao {
    List<customer> getCustomer(@Param("companyName") String companyName);
}
