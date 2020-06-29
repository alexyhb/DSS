package com.service;

import com.pojo.caculate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface caculateService {
    boolean addCaculate(@Param("companyId") int companyId, @Param("customerId")  int customerId, @Param("sacleScore")  int sacleScore, @Param("nationScore")  int nationScore, @Param("quantityScore")  int quantityScore, @Param("totalScore")  int totalScore);
    List<caculate> getCaculate(int companyId);
    void updateAll();
}
