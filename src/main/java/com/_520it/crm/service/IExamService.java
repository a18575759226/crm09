package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.Exam;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IExamService {
	int deleteByPrimaryKey(Long id);
    int insert(Exam record);
    Exam selectByPrimaryKey(Long id);
    List<Exam> selectAll();
    int updateByPrimaryKey(Exam record);
	PageResult queryPage(QueryObject qo);
	void checkByPrimaryKey(Exam exam);
}
