package org.jeapf.framework.web.taglibs.component.builder;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jeapf.framework.web.taglibs.component.TagBuilder;
import org.jeapf.framework.web.taglibs.component.TagVariable;
import org.jeapf.framework.web.taglibs.component.variables.SelectTagVariable;

/**
 * 选择控件构建器（支持select、radio、checkbox选择控件）
 * @author yuqs
 */
public class SelectTagBuilder implements TagBuilder {
	private static final String TYPE_SELECT = "select";
	private static final String TYPE_RADIO = "radio";
	private static final String TYPE_CHECKBOX = "checkbox";
	
	private final static Log log = LogFactory.getLog(SelectTagBuilder.class);
	
	@Override
	public String build(TagVariable variable) {
		if(!(variable instanceof SelectTagVariable)) {
			return "";
		}
		
		if(variable.getName() == null || variable.getType() == null) {
			log.error("please confirm tag name or tag type is null.");
			return "";
		}
		
		SelectTagVariable selectV = (SelectTagVariable)variable;
		StringBuffer buffer = new StringBuffer();

		if(selectV.getType().equalsIgnoreCase(TYPE_CHECKBOX)) {
			buildCheckOrRadio(selectV, buffer);
		} else if(selectV.getType().equalsIgnoreCase(TYPE_RADIO)) {
			buildCheckOrRadio(selectV, buffer);
		} else if(selectV.getType().equalsIgnoreCase(TYPE_SELECT)) {
			if(variable.getDisplayType() != null && !variable.getDisplayType().equalsIgnoreCase("")) {
				if(variable.getDisplayType().equalsIgnoreCase("1")) {
					buildSelectLabel(selectV, buffer);
				} else if(variable.getDisplayType().equalsIgnoreCase("2")) {
					buildSelectText(selectV, buffer);
				} else if(variable.getDisplayType().equalsIgnoreCase("0")) {
					buildSelect(selectV, buffer);
				}
			} else {
				buildSelect(selectV, buffer);
			}
		}
		return buffer.toString();
	}
	
	private void buildSelectLabel(SelectTagVariable variable, StringBuffer buffer) {
		List<String> values = variable.getValues();
		Map<String, String> items = variable.getItems();
		Iterator<String> it = items.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = items.get(key);
			if(values != null && values.contains(key)) {
				buffer.append(value);
			}
		}
	}
	
	private void buildSelectText(SelectTagVariable variable, StringBuffer buffer) {
		List<String> values = variable.getValues();
		Map<String, String> items = variable.getItems();
		Iterator<String> it = items.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = items.get(key);
			if(values != null && values.contains(key)) {
				buffer.append(value);
			}
		}
	}
	
	private void buildCheckOrRadio(SelectTagVariable variable, StringBuffer buffer) {
		Map<String, String> items = variable.getItems();
		Iterator<String> it = items.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = items.get(key);
			String selected = "";
			if (variable.getValues().contains(key)) {
				selected = "checked";
			}
			buffer.append("<label>");
			buffer.append("<input type=\"" + variable.getType() + "\" name=\"" + variable.getName() + "\" value=\"" + key + "\" ");
			buffer.append(selected).append(" ");
			buffer.append("/>" + value);
			buffer.append("</label>");
		}
	}
	
	private void buildSelect(SelectTagVariable variable, StringBuffer buffer) {
		buffer.append("<select ");
		buffer.append(" name=\"" + variable.getName() + "\" ");
		if(variable.getCssClass() != null) {
			buffer.append(" class=\"" + variable.getCssClass() + "\" ");
		}
		if(variable.getStyle() != null) {
			buffer.append(" style=\"" + variable.getStyle() + "\" ");
		}
		if(variable.getReadonly() != null && variable.getReadonly().equalsIgnoreCase("true")) {
			buffer.append(" readonly disabled ");
		}
		buffer.append(">");
		buffer.append("<option value='' selected>------请选择------</option>");
		Map<String, String> items = variable.getItems();
		
		if(items != null && !items.isEmpty()) {
			Iterator<String> it = items.keySet().iterator();
			String selected = "";
			
			while (it.hasNext()) {
				selected = "";
				Object key =  it.next();
				String value = (String)items.get(key);
				
				if (variable.getValues().contains(key.toString())) {
					selected = "selected";
				}
				
				buffer.append(" <option " + selected + " value=\"" + key + "\">");
				buffer.append(value + "</option>");
			}
		}
		
		buffer.append("</select>");
	}
}
