package org.jeapf.framework.config.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 配置实体类
 * @author yuqs
 */
@Entity
@Table(name = "CONF_CONFIG")
public class Config extends ConfigEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7610108657592274423L;
	//枚举名称
	private String name;
	//描述
	private String description;
	//
	private List<ConfigItem> configItems;
	@Column(name = "name", unique = true, nullable = false, length = 200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "description", length = 500)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@OneToMany(mappedBy = "config",cascade = CascadeType.ALL)
	public List<ConfigItem> getConfigItems() {
		return configItems;
	}
	public void setConfigItems(List<ConfigItem> configItems) {
		this.configItems = configItems;
	}
}
