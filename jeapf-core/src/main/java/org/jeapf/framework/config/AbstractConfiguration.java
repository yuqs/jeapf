package org.jeapf.framework.config;

import java.util.Map;

/**
 * 抽象配置类，提供通用的配置方法，接口方法还是交给具体实现类
 * @author yuqs
 */
public abstract class AbstractConfiguration implements Configuration {

	@Override
	public abstract Object getConfigByName(String name);

	/**
	 * 根据配置名称，直接从缓存中获取key为name的缓存对象。并且转换为Map<Long, String>类型
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Long, String> getConfigMapsByName(String name) {
		Object config = getConfigByName(name);
		if(!(config instanceof Map)) {
			throw new ConfigurationException("Type conversion failure");
		}
		return (Map<Long, String>)config;
	}
	
	/**
	 * 根据key获取配置缓存value(约定配置value为Map)
	 * @param key
	 * @return
	 */
	public String getConfig(String key, Long code) {
		Map<Long, String> config = getConfigMapsByName(key);
		return config.get(code);
	}
}
