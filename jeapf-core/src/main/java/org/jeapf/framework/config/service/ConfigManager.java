package org.jeapf.framework.config.service;

import java.util.List;

import org.jeapf.framework.config.dao.ConfigDao;
import org.jeapf.framework.config.entity.Config;
import org.jeapf.framework.orm.Page;
import org.jeapf.framework.orm.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 配置管理类
 * @author yuqs
 */
@Component
@Transactional
public class ConfigManager {
	@Autowired
	private ConfigDao configDao;
	
	/**
	 * 保存配置实体
	 * @param entity
	 */
	public void save(Config entity) {
		configDao.save(entity);
	}
	
	/**
	 * 根据主键ID获取配置实体
	 * @param id
	 * @return
	 */
	public Config get(Long id) {
		return configDao.get(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置实体
	 * @param id
	 */
	public void delete(Long id) {
		configDao.delete(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询配置列表
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Config> findPage(final Page<Config> page, final List<PropertyFilter> filters) {
		return configDao.findPage(page, filters);
	}
}
