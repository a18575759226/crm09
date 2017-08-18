package com._520it.crm.service.impl;

import com._520it.crm.domain.SalesStatement;
import com._520it.crm.mapper.SalesStatementMapper;
import com._520it.crm.query.SalesStatementQueryObject;
import com._520it.crm.service.ISalesStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by asus on 2017/07/17.
 */
@Service
public class SalesStatementServiceImpl implements ISalesStatementService {
    @Autowired
    private SalesStatementMapper salesStatementMapper;
    @Override
    public List<SalesStatement> querySalesStatement(SalesStatementQueryObject qo) {
        return salesStatementMapper.querySalesStatement(qo);
    }
}
