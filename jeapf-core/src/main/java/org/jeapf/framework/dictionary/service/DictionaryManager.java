package org.jeapf.framework.dictionary.service;

import java.util.List;

import org.jeapf.framework.dictionary.dao.DictionaryDao;
import org.jeapf.framework.dictionary.entity.Dictionary;
import org.jeapf.framework.dictionary.entity.DictionaryItem;
import org.jeapf.framework.orm.Page;
import org.jeapf.framework.orm.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 配置字典管理类
 * @author yuqs
 */
@Component
@Transactional
public class DictionaryManager {
	@Autowired
	private DictionaryDao dictionaryDao;
	
	/**
	 * 保存配置字典实体
	 * @param entity
	 */
	public void save(Dictionary entity) {
		dictionaryDao.save(entity);
	}
	
	/**
	 * 保存配置字典实体，更新时先删除配置字典选项列表，再添加选项列表
	 * @param entity
	 */
	public void save(Dictionary entity, List<DictionaryItem> items) {
		if(entity.getId() != null) {
			dictionaryDao.batchExecute("delete DictionaryItem where dictionary=" + entity.getId());
		}
		
		if(items != null && items.size() > 0) {
			entity.setDictionaryItems(items);
		}
		dictionaryDao.save(entity);
	}
	
	/**
	 * 根据主键ID获取配置字典实体
	 * @param id
	 * @return
	 */
	public Dictionary get(Long id) {
		return dictionaryDao.get(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置字典实体
	 * @param id
	 */
	public void delete(Long id) {
		dictionaryDao.delete(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询配置字典列表
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Dictionary> findPage(final Page<Dictionary> page, final List<PropertyFilter> filters) {
		return dictionaryDao.findPage(page, filters);
	}
}
