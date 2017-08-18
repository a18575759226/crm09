package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.Institute;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IInstituteService {
	int deleteByPrimaryKey(Long id);
    int insert(Institute record);
    Institute selectByPrimaryKey(Long id);
    List<Institute> selectAll();
    int updateByPrimaryKey(Institute record);
	PageResult queryPage(QueryObject qo);
}
