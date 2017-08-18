package com._520it.crm.service;
import com._520it.crm.domain.SchoolContact;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface ISchoolContactService {
	int deleteByPrimaryKey(Long id);
    int insert(SchoolContact record);
    SchoolContact selectByPrimaryKey(Long id);
    List<SchoolContact> selectAll();
    int updateByPrimaryKey(SchoolContact record);
	PageResult queryPage(QueryObject qo);
}
