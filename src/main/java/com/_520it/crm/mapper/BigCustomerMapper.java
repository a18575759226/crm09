package com._520it.crm.mapper;

import com._520it.crm.domain.BigCustomer;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BigCustomerMapper {
    int deleteByPrimaryKey(Long id);
    int insert(BigCustomer record);
    BigCustomer selectByPrimaryKey(Long id);
    List<BigCustomer> selectAll();
    int updateByPrimaryKey(BigCustomer record);
	Long queryPageCount(QueryObject qo);
	List<BigCustomer> queryPageData(QueryObject qo);
}