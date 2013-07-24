package org.jeapf.framework.dictionary;

import java.util.Map;

/**
 * 抽象配置字典类，提供通用的配置字典方法，接口方法还是交给具体实现类
 * @author yuqs
 */
public abstract class AbstractDictionary implements IDictionary {

	@Override
	public abstract Map<Long, String> getByName(String name);

	/**
	 * 根据key获取字典缓存value(约定配置value为Map)
	 * @param key
	 * @return
	 */
	public String getValue(String name, Long key) {
		Map<Long, String> config = getByName(name);
		return config.get(key);
	}
}
