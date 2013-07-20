package org.jeapf.framework.security.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

/**
 * 安全实体抽象类
 * @author yuqs
 */
@MappedSuperclass
public abstract class SecurityEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5288872906807628038L;
	//主键标识ID
	protected Long id;

	/**
	 * 安全实体的主键生成策略为序列，序列名称为SEC_SEQUENCE
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="SEC_SEQUENCE", sequenceName="SEC_SEQUENCE")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
