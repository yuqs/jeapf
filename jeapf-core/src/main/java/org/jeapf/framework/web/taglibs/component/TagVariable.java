package org.jeapf.framework.web.taglibs.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TagVariable {
	//控件名称
	private String name;
	//控件类型
	private String type;
	//控件style
	private String style;
	//控件是否只读
	private String readonly;
	//控件css
	private String cssClass;
	//显示类型
	private String displayType;
	//事件集合
	private Map<String, String> events;
	//已保存的值列表
	private List<String> values = new ArrayList<String>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, String> getEvents() {
		return events;
	}
	public void setEvents(Map<String, String> events) {
		this.events = events;
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getReadonly() {
		return readonly;
	}
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
}
