package com._520it.crm.mapper;

import com._520it.crm.domain.LatentNumber;
import com._520it.crm.domain.SalesStatement;
import com._520it.crm.query.LatentNumberQueryObject;
import com._520it.crm.query.SalesStatementQueryObject;

import java.util.List;

/**
 * Created by asus on 2017/07/17.
 */
public interface SalesStatementMapper {
    List<SalesStatement> querySalesStatement(SalesStatementQueryObject qo);
}
