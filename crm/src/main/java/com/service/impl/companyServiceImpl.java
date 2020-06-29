package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dao.companyDao;
import com.pojo.company;
import com.service.companyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class companyServiceImpl implements companyService {

    @Autowired
    private companyDao companyDao;
    @Override
    public List<company> getCompanyList(String industry) {
        return companyDao.getCompanyList(industry);
    }

    @Override
    public boolean addCompany(String industry, String nationality, int quantity, String username, String password, String name,int scale) {

        companyDao.addCompany(industry, nationality, quantity, username, password, name, scale);
        return false;
    }

    @Override
    public int idOfCompany(String username) {
        return companyDao.idOfCompany(username);
    }

    @Override
    public company getCompanyByid(int id) {
        return companyDao.getCompanyByid(id);
    }

    @Override
    public JSONObject Login(String username, String password) {
        JSONObject result = new JSONObject();

        if ("".equals(username) || "".equals(password)) {
            result.put("code","400");
            result.put("msg","username &password shouldn't be blank");
            return result;
        }

        List<company> list = companyDao.getLoginList(username);
        if (list.size() == 0) {
            result.put("code","400");
            result.put("msg","username or password error");

        } else {
            if (!password.equals(list.get(0).getPassword())) {
                result.put("code","400");
                result.put("msg","username or password error");

            }else {
                result.put("code","200");
                result.put("msg","successful");

            }
        }
        return result;

    }


}
