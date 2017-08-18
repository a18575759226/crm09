package com._520it.crm.mapper;

import com._520it.crm.domain.Exam;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Exam record);
    Exam selectByPrimaryKey(Long id);
    List<Exam> selectAll();
    int updateByPrimaryKey(Exam record);
	Long queryPageCount(QueryObject qo);
	List<Exam> queryPageData(QueryObject qo);
	void checkByPrimaryKey(Exam exam);
}