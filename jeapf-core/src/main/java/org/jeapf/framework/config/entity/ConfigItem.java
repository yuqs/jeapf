package org.jeapf.framework.config.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 配置元素实体类
 * @author yuqs
 */
@Entity
@Table(name = "CONF_ITEM")
public class ConfigItem extends ConfigEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1382491728106297904L;
	//枚举值名称
	private  String name;
	//描述
	private String description;
	//枚举值编号
	private Integer orderby;
	//枚举项
	private Config config;
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
	@Column(name = "orderby")
	public Integer getOrderby() {
		return orderby;
	}
	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}
	@ManyToOne
	@JoinColumn(name="config")
	public Config getConfig() {
		return config;
	}
	public void setConfig(Config config) {
		this.config = config;
	}
}
