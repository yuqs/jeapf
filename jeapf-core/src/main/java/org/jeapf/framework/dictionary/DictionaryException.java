package org.jeapf.framework.dictionary;

/**
 * 配置字典异常类
 * @author yuqs
 */
public class DictionaryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7781168239046507965L;

	public DictionaryException(Throwable root) {
		super(root);
	}

	public DictionaryException(String string, Throwable root) {
		super(string, root);
	}

	public DictionaryException(String s) {
		super(s);
	}
}
