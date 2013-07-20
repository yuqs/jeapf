package org.jeapf.framework.web.taglibs.component;

/**
 * 标签构建接口
 * @author yuqs
 */
public interface TagBuilder {
	/**
	 * 根据标签变量构建控件元素
	 * @param variable
	 * @return
	 */
	public String build(TagVariable variable);
}
