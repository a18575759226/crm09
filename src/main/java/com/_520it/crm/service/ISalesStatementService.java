package com._520it.crm.service;

import com._520it.crm.domain.SalesStatement;
import com._520it.crm.query.SalesStatementQueryObject;

import java.util.List;

/**
 * Created by asus on 2017/07/17.
 */
public interface ISalesStatementService {
    List<SalesStatement> querySalesStatement(SalesStatementQueryObject qo);

}
