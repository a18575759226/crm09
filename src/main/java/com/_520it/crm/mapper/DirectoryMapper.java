package com._520it.crm.mapper;

import com._520it.crm.domain.Directory;
import com._520it.crm.query.QueryObject;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DirectoryMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Directory record);
    Directory selectByPrimaryKey(Long id);
    List<Directory> selectAll();
    int updateByPrimaryKey(Directory record);
	Long queryPageCount(QueryObject qo);
	List<Directory> queryPageData(QueryObject qo);
}