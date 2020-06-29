package com.service;

import com.pojo.customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface customerService {
    List<customer> customerList(@Param("companyName") String companyName);
}
