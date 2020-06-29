package com.service.impl;

import com.dao.caculateDao;
import com.dao.companyDao;
import com.pojo.caculate;
import com.pojo.company;
import com.service.caculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class caculateServiceImpl implements caculateService {


    @Autowired
    private caculateDao caculateDao;

    @Autowired
    private companyDao companyDao;

    @Override
    public boolean addCaculate(int companyId, int customerId, int sacleScore, int nationScore, int quantityScore, int totalScore) {

        boolean flag=caculateDao.addCaculate( companyId,  customerId,  sacleScore,  nationScore,  quantityScore,  totalScore);
        return flag;
    }

    @Override
    public List<caculate> getCaculate(int companyId) {
        return caculateDao.getCaculate(companyId);
    }

    @Override
    public void updateAll() {
        List<company> listCompany=companyDao.getList();
        for (int j=0;j<listCompany.size();j++){
            int companyId=listCompany.get(j).getId();
            List<company> list1=companyDao.getCompanyList(listCompany.get(j).getIndustry());
            for (int i=0;i<list1.size();i++){
                int customerId=list1.get(i).getId();
                if(companyId!=customerId){

                    int tempScale = Math.abs(listCompany.get(j).getScale() - list1.get(i).getScale());
                    int sacleScore = 0;
                    if (tempScale < 2000) {
                        sacleScore = 10;
                    } else if (tempScale >= 2000 && tempScale < 4000) {
                        sacleScore = 8;
                    } else if (tempScale >= 4000 && tempScale < 8000) {
                        sacleScore = 6;
                    } else if (tempScale >= 8000 && tempScale < 16000) {
                        sacleScore = 4;
                    } else if (tempScale >= 16000) {
                        sacleScore = 2;
                    }
                    int tempQuantity = Math.abs(listCompany.get(j).getQuantity() - list1.get(i).getQuantity());
                    int quantityScore = 0;
                    if (tempQuantity < 20) {
                        quantityScore = 10;
                    } else if (tempQuantity >= 20 && tempQuantity < 50) {
                        quantityScore = 8;
                    } else if (tempQuantity >= 50 && tempQuantity < 100) {
                        quantityScore = 6;
                    } else if (tempQuantity >= 100 && tempQuantity < 500) {
                        quantityScore = 4;
                    } else if (tempQuantity >= 500) {
                        quantityScore = 2;
                    }
                    int nationScore = 0;
                    if (listCompany.get(j).getNationality() == list1.get(i).getNationality()) {
                        nationScore = 5;
                    }
                    int totolScore = sacleScore + quantityScore + nationScore;
                    if (caculateDao.findCaculate(companyId, customerId) == 1) {
                        caculateDao.updateAll(companyId, customerId, sacleScore, nationScore, quantityScore, totolScore);
                        caculateDao.updateAll(customerId, companyId, sacleScore, nationScore, quantityScore, totolScore);
                    } else {
                        caculateDao.addCaculate(companyId, customerId, sacleScore, nationScore, quantityScore, totolScore);
                        caculateDao.addCaculate(customerId, companyId, sacleScore, nationScore, quantityScore, totolScore);
                    }
                }
            }

        }

    }
}
