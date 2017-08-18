package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.Courselist;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface ICourselistService {
	int deleteByPrimaryKey(Long id);
    int insert(Courselist record);
    Courselist selectByPrimaryKey(Long id);
    List<Courselist> selectAll();
    int updateByPrimaryKey(Courselist record);
	PageResult queryPage(QueryObject qo);
	void checkBycourselistId(Long courselistId);
}
