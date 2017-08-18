package com._520it.crm.service.impl;

import com._520it.crm.domain.Directory;
import com._520it.crm.mapper.DirectoryMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DirectoryQueryObject;
import com._520it.crm.service.IDirectoryService;
import com._520it.crm.vo.DicMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class DirectoryServiceImpl implements IDirectoryService {
	@Autowired
	private DirectoryMapper directoryMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return directoryMapper.deleteByPrimaryKey(id);
	}

	public int insert(Directory record) {
		return directoryMapper.insert(record);
	}

	public Directory selectByPrimaryKey(Long id) {
		return directoryMapper.selectByPrimaryKey(id);
	}

	public List<Directory> selectAll() {
		return directoryMapper.selectAll();
	}

	public int updateByPrimaryKey(Directory record) {
		return directoryMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(DirectoryQueryObject qo) {
		Long count = directoryMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Directory> result = directoryMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

    @Override
    public PageResult listSonDic(DirectoryQueryObject qo) {
        Long count = directoryMapper.queryPageCount(qo);
        if(count<=0){
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Directory> result = directoryMapper.queryPageData(qo);
        PageResult pageResult = new PageResult(count,result);
        return pageResult;
    }

    /**
     * 通过parent_id获取所有的父目录id和名称,并将这两个属性装在集合中
     *
     * @param parentId
     * @return
     */
    public List<DicMenuVO> listParentMenus(Long parentId) {
        List<DicMenuVO> list = new ArrayList<>();
        Directory menu = directoryMapper.selectByPrimaryKey(parentId);
        while (menu != null) {
            DicMenuVO vo = new DicMenuVO();
            vo.setId(menu.getId());
            vo.setName(menu.getName());
            list.add(vo);
            menu = menu.getParent();
        }
        Collections.reverse(list);
        return list;
    }

}
