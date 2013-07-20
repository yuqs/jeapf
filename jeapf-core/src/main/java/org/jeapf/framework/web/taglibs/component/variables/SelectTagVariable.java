package org.jeapf.framework.web.taglibs.component.variables;

import java.util.Map;

import org.jeapf.framework.web.taglibs.component.TagVariable;

public class SelectTagVariable extends TagVariable {
	/**
	 * 配置表中获取的元素集合，用于组装控件
	 */
	private Map<String, String> items;

	public Map<String, String> getItems() {
		return items;
	}

	public void setItems(Map<String, String> items) {
		this.items = items;
	}
}
