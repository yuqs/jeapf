package org.jeapf.framework.security.service;

import java.util.List;

import org.hibernate.SQLQuery;
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
	
	/**
	 * 获取所有菜单
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Menu> getAll() {
		return menuDao.getAll();
	}
	
	/**
	 * 根据用户ID查询该用户允许访问的所有菜单列表
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Menu> getAllowedAccessMenu(Long userId) {
		StringBuffer sqlBuffer = new StringBuffer();
		//获取Sec_Menu表中定义且未关联资源表Sec_Resource的所有菜单列表
		sqlBuffer.append(" select m.id,m.name,m.parent_menu,m.description from sec_menu m ");
		sqlBuffer.append(" where not exists (select re.id from sec_resource re where re.menu = m.id)" );
		sqlBuffer.append(" union all ");
		//获取Sec_Resource表中已关联且未设置权限的菜单列表
		sqlBuffer.append(" select m.id,m.name,m.parent_menu,re.source as description from sec_resource re ");
		sqlBuffer.append(" left outer join sec_menu m on re.menu = m.id  ");
		sqlBuffer.append(" where re.menu is not null and not exists (select ar.authority_id from sec_authority_resource ar where ar.resource_id = re.id)");
		sqlBuffer.append(" union all ");
		//获取Sec_Resource表中已关联且设置权限，并根据当前登录账号拥有相应权限的菜单列表
		sqlBuffer.append(" select m.id,m.name,m.parent_menu,re.source as description from sec_user u ");
		sqlBuffer.append(" left outer join sec_role_user ru on u.id=ru.user_id ");
		sqlBuffer.append(" left outer join sec_role r on ru.role_id=r.id ");
		sqlBuffer.append(" left outer join sec_role_authority ra on r.id = ra.role_id ");
		sqlBuffer.append(" left outer join sec_authority a on ra.authority_id = a.id ");
		sqlBuffer.append(" left outer join sec_authority_resource ar on a.id = ar.authority_id ");
		sqlBuffer.append(" left outer join sec_resource re on ar.resource_id = re.id ");
		sqlBuffer.append(" left outer join sec_menu m on re.menu = m.id ");
		sqlBuffer.append(" where u.id=? and re.menu is not null ");
		SQLQuery query = menuDao.createSQLQuery(sqlBuffer.toString(), userId);
		query.addEntity(Menu.class);
		return query.list();
	}
}
