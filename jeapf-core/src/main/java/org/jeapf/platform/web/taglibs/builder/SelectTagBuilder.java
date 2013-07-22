package org.jeapf.platform.web.taglibs.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeapf.framework.web.TagBuilder;
import org.jeapf.framework.web.TagDTO;
import org.jeapf.platform.service.ConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 选择控件构建器（支持select、radio、checkbox选择控件）
 * @author yuqs
 */
@Component
public class SelectTagBuilder implements TagBuilder {
	private final static Log log = LogFactory.getLog(SelectTagBuilder.class);
	private static final String TYPE_SELECT = "select";
	private static final String TYPE_RADIO = "radio";
	private static final String TYPE_CHECKBOX = "checkbox";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String CONFIGNAME = "configName";
	public static final String STYLE = "style";
	public static final String READONLY = "readonly";
	public static final String CSSCLASS = "cssClass";
	public static final String DISPLAYTYPE = "displayType";
	public static final String VALUE = "value";
	
	//@Autowired
	private ConfigManager configManager;
	//控件名称
	private String name;
	//控件类型
	private String type;
	//配置实体名称
	private String configName;
	//对象已选择的值
	private String value;
	//控件style
	private String style;
	//控件是否只读
	private String readonly;
	//控件css
	private String cssClass;
	//显示类型
	private String displayType;
	//值列表
	private List<String> values = new ArrayList<String>();
	//选择列表
	private Map<String, String> items = new TreeMap<String, String>();
	
	/**
	 * 获取DTO传递的参数，并依此构建选择控件的html信息
	 */
	@Override
	public String build(TagDTO dto) {
		if(name == null || type == null) {
			log.error("please confirm tag name or tag type is null.");
			return "";
		}
		dataProcess(dto);
		StringBuffer buffer = new StringBuffer();
		if(type.equalsIgnoreCase(TYPE_CHECKBOX)) {
			buildCheckOrRadio(buffer);
		} else if(type.equalsIgnoreCase(TYPE_RADIO)) {
			buildCheckOrRadio(buffer);
		} else if(type.equalsIgnoreCase(TYPE_SELECT)) {
			if(displayType != null && !displayType.equalsIgnoreCase("")) {
				if(displayType.equalsIgnoreCase("1")) {
					buildSelectLabel(buffer);
				} else if(displayType.equalsIgnoreCase("0")) {
					buildSelect(buffer);
				}
			} else {
				buildSelect(buffer);
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 根据dto对象，转换为builder的对象属性。并且对于configName，从注入的ConfigManager对象中获取配置数据
	 * @param dto
	 */
	@SuppressWarnings("unchecked")
	private void dataProcess(TagDTO dto) {
		name = dto.getProperty(NAME);
		type = dto.getProperty(TYPE);
		configName = dto.getProperty(CONFIGNAME);
		value = dto.getProperty(VALUE);
		style = dto.getProperty(STYLE);
		readonly = dto.getProperty(READONLY);
		cssClass = dto.getProperty(CSSCLASS);
		displayType = dto.getProperty(DISPLAYTYPE);
		if(value != null && !value.equals("")) {
			String[] vs = value.split(";");
			for(String v : vs) {
				values.add(v);
			}
		}
		items = (Map<String, String>)configManager.getConfigByName(configName);
	}
	
	/**
	 * 选择结果已文本显示
	 * @param buffer
	 */
	private void buildSelectLabel(StringBuffer buffer) {
		Iterator<String> it = items.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = items.get(key);
			if(values != null && values.contains(key)) {
				buffer.append(value);
			}
		}
	}
	
	/**
	 * 构造checkbox、radio类型的控件元素
	 * @param buffer
	 */
	private void buildCheckOrRadio(StringBuffer buffer) {
		Iterator<String> it = items.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = items.get(key);
			String selected = "";
			if (values != null && values.contains(key)) {
				selected = "checked";
			}
			buffer.append("<label>");
			buffer.append("<input type=\"" + type + "\" name=\"" + name + "\" value=\"" + key + "\" ");
			buffer.append(selected).append(" ");
			buffer.append("/>" + value);
			buffer.append("</label>");
		}
	}
	
	/**
	 * 构造select类型的控件元素
	 * @param buffer
	 */
	private void buildSelect(StringBuffer buffer) {
		buffer.append("<select ");
		buffer.append(" name=\"" + name + "\" ");
		if(cssClass != null) {
			buffer.append(" class=\"" + cssClass + "\" ");
		}
		if(style != null) {
			buffer.append(" style=\"" + style + "\" ");
		}
		if(readonly != null && readonly.equalsIgnoreCase("true")) {
			buffer.append(" readonly disabled ");
		}
		buffer.append(">");
		buffer.append("<option value='' selected>------请选择------</option>");
		
		if(items != null && !items.isEmpty()) {
			Iterator<String> it = items.keySet().iterator();
			String selected = "";
			
			while (it.hasNext()) {
				selected = "";
				Object key =  it.next();
				String value = (String)items.get(key);
				
				if (values != null && values.contains(key.toString())) {
					selected = "selected";
				}
				
				buffer.append(" <option " + selected + " value=\"" + key + "\">");
				buffer.append(value + "</option>");
			}
		}
		
		buffer.append("</select>");
	}
}
