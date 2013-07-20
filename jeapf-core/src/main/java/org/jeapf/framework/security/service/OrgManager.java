package org.jeapf.framework.security.service;

import java.util.List;

import org.jeapf.framework.orm.Page;
import org.jeapf.framework.orm.PropertyFilter;
import org.jeapf.framework.security.dao.OrgDao;
import org.jeapf.framework.security.entity.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 部门管理类
 * @author yuqs
 */
@Component
@Transactional
public class OrgManager {
	//注入部门持久化对象
	@Autowired
	private OrgDao orgDao;
	
	/**
	 * 保存部门实体
	 * @param entity
	 */
	public void save(Org entity) {
		orgDao.save(entity);
	}
	
	/**
	 * 根据主键ID删除对应的部门
	 * @param id
	 */
	public void delete(Long id) {
		orgDao.delete(id);
	}
	
	/**
	 * 根据主键ID获取部门实体
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Org get(Long id) {
		return orgDao.get(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询部门列表
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Org> findPage(final Page<Org> page, final List<PropertyFilter> filters) {
		return orgDao.findPage(page, filters);
	}
	
	/**
	 * 获取所有部门记录
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Org> getAll() {
		return orgDao.getAll();
	}
}
