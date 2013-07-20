package org.jeapf.framework.security.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jeapf.framework.security.entity.User;

/**
 * 自定义认证主体
 * @author yuqs
 */
public class ShiroPrincipal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1428196040744555722L;
	//用户ID
	private Long id;
	//用户账号名称
	private String username;
	//用户姓名
	private String fullname;
	//用户所在部门ID
	private Long orgId;
	//用户所在部门名称
	private String orgName;
	//用户权限列表
	private List<String> authorities = new ArrayList<String>();
	//用户角色列表
	private List<String> roles = new ArrayList<String>();
	//是否已授权。如果已授权，则不需要再从数据库中获取权限信息，减少数据库访问
	//这里会导致修改权限时，需要重新登录方可有效
	private boolean isAuthorized = false;
	
	/**
	 * 构造函数，参数为User对象
	 * 根据User对象属性，赋值给Principal相应的属性上
	 * @param user
	 */
	public ShiroPrincipal(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.fullname = user.getFullname();
		if(user.getOrg() != null) {
			this.orgId = user.getOrg().getId();
			this.orgName = user.getOrg().getName();
		}
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public boolean isAuthorized() {
		return isAuthorized;
	}
	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}
	
	/**
	 * <shiro:principal/>标签显示中文名称
	 */
	@Override
	public String toString() {
	    return this.fullname;
	}
}
