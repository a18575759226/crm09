package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.SystemMenu;
import com._520it.crm.mapper.SystemMenuMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ISystemMenuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
	@Autowired
	private SystemMenuMapper systemMenuMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return systemMenuMapper.deleteByPrimaryKey(id);
	}

	public int insert(SystemMenu record) {
		return systemMenuMapper.insert(record);
	}

	public SystemMenu selectByPrimaryKey(Long id) {
		return systemMenuMapper.selectByPrimaryKey(id);
	}

	public List<SystemMenu> selectAll() {
		return systemMenuMapper.selectAll();
	}

	public int updateByPrimaryKey(SystemMenu record) {
		return systemMenuMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = systemMenuMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<SystemMenu> result = systemMenuMapper.queryPageDataResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public List<SystemMenu> queryTree() {
		return systemMenuMapper.queryTree();
	}

	@Override
	public List<SystemMenu> queryForRole() {
		return systemMenuMapper.queryTree();
	}

	@Override
	public List<Long> queryMenuIdsListForRole(Long roleId) {
		return systemMenuMapper.systemMenuMapper(roleId);
	}

	@Override
	public List<SystemMenu> indexMenu() {
        //获取所有的菜单
        List<SystemMenu> allMenus = systemMenuMapper.queryTree();
        //如果用户是管理员,拥有所有系统菜单;否则查询出数据库中的按钮
        Employee currentUser = (Employee) SecurityUtils.getSubject().getPrincipal();
        if (currentUser.isAdmin()) {
            return allMenus;
        }else{
            //根据用户id获取该用户的系统菜单id集合
            List<Long> ids = systemMenuMapper.queryPermissionIdsByEmployeeId(currentUser.getId());
            filterMenu(allMenus,ids);
        }
        return allMenus;
	}

    public void filterMenu(List<SystemMenu> allMenus,List<Long> ids){
        SystemMenu menu = null;
        for (int i = allMenus.size() - 1; i >= 0; i--) {
            menu = allMenus.get(i);
            if (!ids.contains(menu.getId())) {
                allMenus.remove(i);
                continue;
            }
            List<SystemMenu> children = menu.getChildren();
            if (children != null && children.size() > 0) {
                filterMenu(children,ids);
            }
        }
    }
}
