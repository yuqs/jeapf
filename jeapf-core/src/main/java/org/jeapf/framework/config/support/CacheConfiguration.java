package org.jeapf.framework.config.support;

import org.jeapf.framework.cache.CacheManager;
import org.jeapf.framework.config.AbstractConfiguration;
import org.springframework.stereotype.Component;

/**
 * 缓存配置管理支持类，从缓存中获取配置数据，提供给需要配置数据的对象
 * @author yuqs
 */
@Component
public class CacheConfiguration extends AbstractConfiguration {
	/**
	 * 根据配置名称，直接从缓存中获取key为name的缓存对象。对于配置数据而言，一般为Map<Long, String>类型
	 * @param name
	 * @return
	 */
	@Override
	public Object getConfigByName(String name) {
		return CacheManager.get(name);
	}
}
