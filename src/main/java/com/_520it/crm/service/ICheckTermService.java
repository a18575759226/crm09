package com._520it.crm.service;

import com._520it.crm.domain.CheckTerm;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CheckTermQueryObject;
import com._520it.crm.query.QueryObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICheckTermService {
	int deleteByPrimaryKey(Long id);
    void signIn(HttpServletRequest request);
    CheckTerm selectByPrimaryKey(Long id);
    List<CheckTerm> selectAll();
	PageResult queryPage(QueryObject qo);
    /*签退功能*/
    void signOut();
    //补签操作
    void patch();

    List<CheckTerm> selectByCondition(CheckTermQueryObject qo);
}
