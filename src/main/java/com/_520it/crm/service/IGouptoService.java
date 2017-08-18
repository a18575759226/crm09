package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.Goupto;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IGouptoService {
	int deleteByPrimaryKey(Long id);
    int insert(Goupto record);
    Goupto selectByPrimaryKey(Long id);
    List<Goupto> selectAll();
    int updateByPrimaryKey(Goupto record);
	PageResult queryPage(QueryObject qo);
}
