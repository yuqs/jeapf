package org.jeapf.framework.config;

/**
 * 配置管理接口，向平台提供基础配置数据
 * @author yuqs
 */
public interface Configuration {
	/**
	 * 根据配置名称，获取配置数据对象
	 * @param name
	 * @return
	 */
	public abstract Object getConfigByName(String name);
}
