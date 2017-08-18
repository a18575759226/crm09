package com._520it.crm.mapper;

import com._520it.crm.domain.TurnoverStudent;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TurnoverStudentMapper {
    int deleteByPrimaryKey(Long id);
    int insert(TurnoverStudent record);
    TurnoverStudent selectByPrimaryKey(Long id);
    List<TurnoverStudent> selectAll();
    int updateByPrimaryKey(TurnoverStudent record);
	Long queryPageCount(QueryObject qo);
	List<TurnoverStudent> queryPageData(QueryObject qo);
}