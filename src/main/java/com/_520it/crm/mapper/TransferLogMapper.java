package com._520it.crm.mapper;

import com._520it.crm.domain.TransferLog;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface TransferLogMapper {
    int deleteByPrimaryKey(Long id);
    int insert(TransferLog record);
    TransferLog selectByPrimaryKey(Long id);
    List<TransferLog> selectAll();
    int updateByPrimaryKey(TransferLog record);
	Long queryPageCount(QueryObject qo);
	List<TransferLog> queryPageData(QueryObject qo);

    void updateState(TransferLog transferLog);

    TransferLog getLogByCustomerId(Long customerId);
}