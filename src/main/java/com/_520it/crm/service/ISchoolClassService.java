package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.SchoolClass;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface ISchoolClassService {
	int deleteByPrimaryKey(Long id);
    int insert(SchoolClass record);
    SchoolClass selectByPrimaryKey(Long id);
    List<SchoolClass> selectAll();
    int updateByPrimaryKey(SchoolClass record);
	PageResult queryPage(QueryObject qo);
	Long selectByPrimaryRealname(String className);
}
