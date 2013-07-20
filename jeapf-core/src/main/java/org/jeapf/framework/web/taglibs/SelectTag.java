package org.jeapf.framework.web.taglibs;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jeapf.framework.cache.CacheManager;
import org.jeapf.framework.web.taglibs.component.TagBuilder;
import org.jeapf.framework.web.taglibs.component.variables.SelectTagVariable;

public class SelectTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3678484078716531743L;
	
	/**
	 * 选择类型：select/radio/checkbox
	 */
	private String type;
	
	/**
	 * 字段名称
	 */
	private String name;
	
	/**
	 * 配置实体名称
	 */
	private String configName;
	
	/**
	 * 是否只读
	 */
	private String readOnly;
	
	/**
	 * 对象已选择的值
	 */
	private String value;
	
	/**
	 * 控件样式
	 */
	private String style;
	
	/**
	 * 控件css
	 */
	private String cssClass;
	
	/**
	 * 是否文本显示
	 */
	private String displayType;

	@SuppressWarnings("unchecked")
	public int doEndTag() throws JspException {
		TagBuilder builder = TagBuilderFactory.getSelectTagBuilder();
		
		SelectTagVariable variable = new SelectTagVariable();
		variable.setType(type);
		variable.setName(name);
		variable.setStyle(style);
		variable.setCssClass(cssClass);
		variable.setReadonly(readOnly);
		variable.setDisplayType(displayType);
		if(value != null && !value.equals("")) {
			String[] values = value.split(";");
			for(String v : values) {
				variable.getValues().add(v);
			}
		}
		
		Object object = CacheManager.get(configName);
		if(object != null && object instanceof Map) {
			variable.setItems((Map<String, String>)object);
		}
		JspWriter writer = pageContext.getOut();
		try {
			writer.write(builder.build(variable));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
}
