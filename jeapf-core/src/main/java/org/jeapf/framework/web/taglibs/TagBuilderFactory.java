package org.jeapf.framework.web.taglibs;

import org.jeapf.framework.web.taglibs.component.TagBuilder;
import org.jeapf.framework.web.taglibs.component.builder.SelectTagBuilder;

public class TagBuilderFactory {
	public static TagBuilder getSelectTagBuilder() {
		return new SelectTagBuilder();
	}
}
