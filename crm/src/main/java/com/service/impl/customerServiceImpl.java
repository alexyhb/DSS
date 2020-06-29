package com.service.impl;

import com.dao.customerDao;

import com.pojo.customer;
import com.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class customerServiceImpl implements customerService {
    @Autowired
    private customerDao customerDao;
    @Override
    public List<customer> customerList(String companyName) {
        return customerDao.getCustomer(companyName);
    }

}
