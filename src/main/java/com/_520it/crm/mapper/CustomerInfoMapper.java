package com._520it.crm.mapper;

import com._520it.crm.domain.CustomerInfo;
import com._520it.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerInfoMapper {
    int deleteByPrimaryKey(Long id);
    int insert(CustomerInfo record);
    CustomerInfo selectByPrimaryKey(Long id);
    List<CustomerInfo> selectAll();
    int updateByPrimaryKey(CustomerInfo record);
	Long queryPageCount(QueryObject qo);
	List<CustomerInfo> queryPageData(QueryObject qo);

    void becomeFormalStudent(Long customerInfoId);

    void moveToPotentialClientPool(Long customerInfoId);

    void updateClassId(CustomerInfo customerInfo);

    void updatePrincipal(@Param("principalId") Long principalId,@Param("customerId")Long customerId);
}