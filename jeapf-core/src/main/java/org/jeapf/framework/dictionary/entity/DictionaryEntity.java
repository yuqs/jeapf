package org.jeapf.framework.dictionary.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

/**
 *配置实体抽象类
 * @author yuqs
 */
@MappedSuperclass
public abstract class DictionaryEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5288872906807628038L;
	//主键标识ID
	protected Long id;

	/**
	 * 配置实体的主键生成策略为序列，序列名称为CONF_SEQUENCE
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="CONF_SEQUENCE", sequenceName="CONF_SEQUENCE")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
