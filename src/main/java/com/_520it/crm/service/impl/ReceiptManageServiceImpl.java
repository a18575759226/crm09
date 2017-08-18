package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com._520it.crm.query.ReceiptManageQueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.ReceiptManage;
import com._520it.crm.mapper.ReceiptManageMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IReceiptManageService;

@Service
public class ReceiptManageServiceImpl implements IReceiptManageService {
    @Autowired
    private ReceiptManageMapper receiptManageMapper;

    public int deleteByPrimaryKey(Long id) {
        return receiptManageMapper.deleteByPrimaryKey(id);
    }

    public int insert(ReceiptManage record) {
        return receiptManageMapper.insert(record);
    }

    public ReceiptManage selectByPrimaryKey(Long id) {
        return receiptManageMapper.selectByPrimaryKey(id);
    }

    public List<ReceiptManage> selectAll() {
        return receiptManageMapper.selectAll();
    }

    public int updateByPrimaryKey(ReceiptManage record) {
        return receiptManageMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        Long count = receiptManageMapper.queryPageCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<ReceiptManage> result = receiptManageMapper.queryPageData(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }

    @Override
    public PageResult expendMethodByReceiptManage(ReceiptManageQueryObject qo) {
        return receiptManageMapper.expendMethodByReceiptManage(qo);
    }
}
