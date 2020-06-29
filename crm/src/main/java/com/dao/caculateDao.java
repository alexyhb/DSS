package com.dao;

import com.pojo.caculate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface caculateDao {
    boolean addCaculate(@Param("companyId") int companyId,@Param("customerId")  int customerId,@Param("sacleScore")  int sacleScore,@Param("nationScore")  int nationScore,@Param("quantityScore")  int quantityScore,@Param("totalScore")  int totalScore);
    boolean updateAll(@Param("companyId") int companyId,@Param("customerId")  int customerId,@Param("sacleScore")  int sacleScore,@Param("nationScore")  int nationScore,@Param("quantityScore")  int quantityScore,@Param("totalScore")  int totalScore);
    int findCaculate(@Param("companyId") int companyId,@Param("customerId") int customerId);
    List<caculate> getAll();
    List<caculate> getCaculate(@Param("companyId") int companyId);
}
