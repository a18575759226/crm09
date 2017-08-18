package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.PotentialCustomer;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IPotentialCustomerService {
	int deleteByPrimaryKey(Long id);
    int insert(PotentialCustomer record);
    PotentialCustomer selectByPrimaryKey(Long id);
    List<PotentialCustomer> selectAll();
    int updateByPrimaryKey(PotentialCustomer record);
	PageResult queryPage(QueryObject qo);

    void getOutToCustomerInfo(Long potentialCustomerId);
}
