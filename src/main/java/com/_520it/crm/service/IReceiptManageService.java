package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.ReceiptManage;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.query.ReceiptManageQueryObject;

public interface IReceiptManageService {
	int deleteByPrimaryKey(Long id);
    int insert(ReceiptManage record);
    ReceiptManage selectByPrimaryKey(Long id);
    List<ReceiptManage> selectAll();
    int updateByPrimaryKey(ReceiptManage record);
	PageResult queryPage(QueryObject qo);

    PageResult expendMethodByReceiptManage(ReceiptManageQueryObject qo);
}
