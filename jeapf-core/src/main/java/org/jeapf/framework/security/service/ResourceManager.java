package org.jeapf.framework.security.service;

import java.util.List;

import org.jeapf.framework.orm.Page;
import org.jeapf.framework.orm.PropertyFilter;
import org.jeapf.framework.security.dao.ResourceDao;
import org.jeapf.framework.security.entity.Authority;
import org.jeapf.framework.security.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资源管理类
 * @author yuqs
 */
@Component
@Transactional
public class ResourceManager {
	//注入资源持久化对象
	@Autowired
	private ResourceDao resourceDao;
	//注入权限持久化对象
	@Autowired
	private AuthorityManager authorityManager;
	
	/**
	 * 保存资源实体
	 * @param entity
	 */
	public void save(Resource entity) {
		resourceDao.save(entity);
	}
	
	/**
	 * 根据主键ID删除资源实体
	 * @param id
	 */
	public void delete(Long id) {
		List<Authority> authorities = authorityManager.getAll();
		for(Authority auth : authorities) {
			for(Resource resource : auth.getResources()) {
				if(resource.getId().longValue() == id.longValue()) {
					auth.getResources().remove(resource);
					authorityManager.save(auth);
					break;
				}
			}
		}
		resourceDao.delete(id);
	}
	
	/**
	 * 根据主键ID获取资源实体
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Resource get(Long id) {
		return resourceDao.get(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询资源列表
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Resource> findPage(final Page<Resource> page, final List<PropertyFilter> filters) {
		return resourceDao.findPage(page, filters);
	}
	
	/**
	 * 查询所有资源记录
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Resource> getAll() {
		return resourceDao.getAll();
	}
}
