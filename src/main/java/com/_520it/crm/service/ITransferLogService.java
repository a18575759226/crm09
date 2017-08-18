package com._520it.crm.service;
import com._520it.crm.domain.TransferLog;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.query.TransferLogQueryObject;

import java.util.List;

public interface ITransferLogService {
	int deleteByPrimaryKey(Long id);
    int insert(TransferLog record);
    TransferLog selectByPrimaryKey(Long id);
    List<TransferLog> selectAll();
    int updateByPrimaryKey(TransferLog record);
	PageResult queryPage(TransferLogQueryObject qo);

    /**
     * 移交记录
     * @param transferLog
     */
    void transfer(TransferLog transferLog);

    /**
     * 审核移交记录
     * @param state
     */
    void transferCheck(TransferLog transferLog);

    TransferLog getLogByCustomerId(Long customerId);
}
