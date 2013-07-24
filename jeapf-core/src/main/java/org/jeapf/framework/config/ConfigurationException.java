package org.jeapf.framework.config;

/**
 * 配置异常类
 * @author yuqs
 */
public class ConfigurationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7781168239046507965L;

	public ConfigurationException(Throwable root) {
		super(root);
	}

	public ConfigurationException(String string, Throwable root) {
		super(string, root);
	}

	public ConfigurationException(String s) {
		super(s);
	}
}
