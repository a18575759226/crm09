package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.BigCustomer;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IBigCustomerService {
	int deleteByPrimaryKey(Long id);
    int insert(BigCustomer record);
    BigCustomer selectByPrimaryKey(Long id);
    List<BigCustomer> selectAll();
    int updateByPrimaryKey(BigCustomer record);
	PageResult queryPage(QueryObject qo);
}
