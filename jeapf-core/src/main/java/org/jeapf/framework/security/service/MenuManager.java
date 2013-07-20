package org.jeapf.framework.security.service;

import java.util.List;

import org.jeapf.framework.orm.Page;
import org.jeapf.framework.orm.PropertyFilter;
import org.jeapf.framework.security.dao.MenuDao;
import org.jeapf.framework.security.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单管理类
 * @author yuqs
 */
@Component
@Transactional
public class MenuManager {
	//注入菜单持久化对象
	@Autowired
	private MenuDao menuDao;
	
	/**
	 * 保存菜单实体
	 * @param entity
	 */
	public void save(Menu entity) {
		menuDao.save(entity);
	}
	
	/**
	 * 根据主键ID删除菜单实体
	 * @param id
	 */
	public void delete(Long id) {
		menuDao.delete(id);
	}
	
	/**
	 * 根据主键ID获取菜单实体
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Menu get(Long id) {
		return menuDao.get(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询菜单列表
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Menu> findPage(final Page<Menu> page, final List<PropertyFilter> filters) {
		return menuDao.findPage(page, filters);
	}
}
