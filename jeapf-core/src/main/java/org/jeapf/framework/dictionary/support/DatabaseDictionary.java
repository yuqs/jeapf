package org.jeapf.framework.dictionary.support;

import java.util.Map;

import org.jeapf.framework.dictionary.AbstractDictionary;
import org.jeapf.framework.dictionary.service.DictionaryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据库配置管理支持类，从数据库获取配置数据，提供给需要配置数据的对象
 * @author yuqs
 */
@Component
public class DatabaseDictionary extends AbstractDictionary {
	@Autowired
	private DictionaryManager dictionaryManager;
	@Override
	public Map<Long, String> getByName(String name) {
		return dictionaryManager.getItemsByName(name);
	}

}
