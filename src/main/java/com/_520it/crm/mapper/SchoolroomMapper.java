package com._520it.crm.mapper;

import com._520it.crm.domain.Schoolroom;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolroomMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Schoolroom record);
    Schoolroom selectByPrimaryKey(Long id);
    List<Schoolroom> selectAll();
    int updateByPrimaryKey(Schoolroom record);
	Long queryPageCount(QueryObject qo);
	List<Schoolroom> queryPageData(QueryObject qo);
	void checkByschoolroomId(@Param("roomId")Long roomId, @Param("state")int state);
	List<Schoolroom> selectListForSchoolroomlistForm();
	Long selectByPrimaryRealname(String roomname);
}