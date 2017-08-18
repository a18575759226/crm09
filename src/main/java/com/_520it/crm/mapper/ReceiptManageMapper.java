package com._520it.crm.mapper;

import com._520it.crm.domain.ReceiptManage;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import java.util.List;

import com._520it.crm.query.ReceiptManageQueryObject;
import org.apache.ibatis.annotations.Param;

public interface ReceiptManageMapper {
    int deleteByPrimaryKey(Long id);
    int insert(ReceiptManage record);
    ReceiptManage selectByPrimaryKey(Long id);
    List<ReceiptManage> selectAll();
    int updateByPrimaryKey(ReceiptManage record);
	Long queryPageCount(QueryObject qo);
	List<ReceiptManage> queryPageData(QueryObject qo);

    PageResult expendMethodByReceiptManage(ReceiptManageQueryObject qo);
}