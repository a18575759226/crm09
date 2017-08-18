package com._520it.crm.service;

import com._520it.crm.domain.Directory;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DirectoryQueryObject;
import com._520it.crm.vo.DicMenuVO;

import java.util.List;

public interface IDirectoryService {
	int deleteByPrimaryKey(Long id);
    int insert(Directory record);
    Directory selectByPrimaryKey(Long id);
    List<Directory> selectAll();
    int updateByPrimaryKey(Directory record);
	PageResult queryPage(DirectoryQueryObject qo);

    /**
     * 根据父目录查询所有的子目录
     * @param qo
     * @return
     */
    PageResult listSonDic(DirectoryQueryObject qo);

    List<DicMenuVO> listParentMenus(Long parentId);
}
