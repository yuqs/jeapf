package org.jeapf.framework.dictionary.support;

import java.util.Map;

import org.jeapf.framework.dictionary.AbstractDictionary;

/**
 * 数据库配置管理支持类，从数据库获取配置数据，提供给需要配置数据的对象
 * @author yuqs
 */
public class DatabaseDictionary extends AbstractDictionary {
	@Override
	public Map<Long, String> getByName(String name) {
		return null;
	}

}
