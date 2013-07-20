package org.jeapf.framework.security.service;

import java.util.List;

import org.jeapf.framework.orm.Page;
import org.jeapf.framework.orm.PropertyFilter;
import org.jeapf.framework.security.dao.RoleDao;
import org.jeapf.framework.security.entity.Role;
import org.jeapf.framework.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色管理类
 * @author yuqs
 */
@Component
@Transactional
public class RoleManager {
	//注入角色持久化对象
	@Autowired
	private RoleDao roleDao;
	//注入用户管理对象
	@Autowired
	private UserManager userManager;
	
	/**
	 * 保存、更新角色实体
	 * @param entity
	 */
	public void save(Role entity) {
		roleDao.save(entity);
	}
	
	/**
	 * 根据主键ID删除对应的角色
	 * @param id
	 */
	public void delete(Long id) {
		List<User> users = userManager.getAll();
		for(User user : users) {
			for(Role role : user.getRoles()) {
				if(role.getId().longValue() == id.longValue()) {
					user.getRoles().remove(role);
					userManager.save(user);
					break;
				}
			}
		}
		roleDao.delete(id);
	}
	
	/**
	 * 根据主键ID获取角色实体
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Role get(Long id) {
		return roleDao.get(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询角色列表
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Role> findPage(final Page<Role> page, final List<PropertyFilter> filters) {
		return roleDao.findPage(page, filters);
	}
	
	/**
	 * 获取所有角色记录
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Role> getAll() {
		return roleDao.getAll();
	}
}
