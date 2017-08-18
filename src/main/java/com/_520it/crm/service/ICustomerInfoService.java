package com._520it.crm.service;
import com._520it.crm.domain.CustomerInfo;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CustomerInfoQueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomerInfoService {
	int deleteByPrimaryKey(Long id);
    int insert(CustomerInfo record);
    CustomerInfo selectByPrimaryKey(Long id);
    List<CustomerInfo> selectAll();
    int updateByPrimaryKey(CustomerInfo record);
	PageResult queryPage(CustomerInfoQueryObject qo);

    void becomeFormalStudent(Long customerInfoId);

    void moveToPotentialClientPool(Long customerInfoId);

    void updateClassId(CustomerInfo customerInfo ,Long customerInfoId);
    void updatePrincipal(Long principalId,Long customerId);
    void quit(Long customerInfoId);

    void loan(Long customerInfoId);

    void affirmPay(Long customerInfoId, BigDecimal pay);
}
