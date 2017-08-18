package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.Schoolroom;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface ISchoolroomService {
	int deleteByPrimaryKey(Long id);
    int insert(Schoolroom record);
    Schoolroom selectByPrimaryKey(Long id);
    List<Schoolroom> selectAll();
    int updateByPrimaryKey(Schoolroom record);
	PageResult queryPage(QueryObject qo);
	void checkByschoolroomId(Long roomId);
	List<Schoolroom> selectListForSchoolroomlistForm();
	Long selectByPrimaryRealname(String roomname);
}
