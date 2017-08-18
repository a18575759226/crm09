package com._520it.crm.mapper;

import com._520it.crm.domain.PotentialCustomer;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PotentialCustomerMapper {
    int deleteByPrimaryKey(Long id);
    int insert(PotentialCustomer record);
    PotentialCustomer selectByPrimaryKey(Long id);
    List<PotentialCustomer> selectAll();
    int updateByPrimaryKey(PotentialCustomer record);
	Long queryPageCount(QueryObject qo);
	List<PotentialCustomer> queryPageData(QueryObject qo);
}