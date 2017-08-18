package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.TrakcsStudents;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface ITrakcsStudentsService {
	int deleteByPrimaryKey(Long id);
    int insert(TrakcsStudents record);
    TrakcsStudents selectByPrimaryKey(Long id);
    List<TrakcsStudents> selectAll();
    int updateByPrimaryKey(TrakcsStudents record);
	PageResult queryPage(QueryObject qo);
}
