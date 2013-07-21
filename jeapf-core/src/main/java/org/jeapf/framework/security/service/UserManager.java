package org.jeapf.framework.security.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.jeapf.framework.orm.Page;
import org.jeapf.framework.orm.PropertyFilter;
import org.jeapf.framework.security.dao.UserDao;
import org.jeapf.framework.security.entity.Org;
import org.jeapf.framework.security.entity.User;
import org.jeapf.framework.utils.encode.EncodeUtils;
import org.jeapf.framework.utils.security.Digests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理类
 * @author yuqs
 */
@Component
@Transactional
public class UserManager {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	//注入用户持久化对象
	@Autowired
	private UserDao userDao;
	
	/**
	 * 保存、更新用户实体
	 * @param entity
	 */
	public void save(User entity) {
		if (StringUtils.isNotBlank(entity.getPlainPassword())) {
			entryptPassword(entity);
		}
		userDao.save(entity);
	}
	
	/**
	 * 根据主键ID删除对应的用户实体
	 * @param id
	 */
	public void delete(Long id) {
		userDao.delete(id);
	}
	
	/**
	 * 根据主键ID获取用户实体
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public User get(Long id) {
		return userDao.get(id);
	}
	
	/**
	 * 根据用户名称，获取用户实体
	 * @param username
	 * @return
	 */
	@Transactional(readOnly = true)
	public User findUserByName(String username) {
		return userDao.findUniqueBy("username", username);
	}
	
	/**
	 * 根据用户名判断是否唯一
	 * @param newUserName
	 * @param oldUserName
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isUserNameUnique(String newUserName, String oldUserName) {
		return userDao.isPropertyUnique("username", newUserName, oldUserName);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询用户列表
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<User> findPage(final Page<User> page, final List<PropertyFilter> filters) {
		return userDao.findPage(page, filters);
	}
	
	/**
	 * 根据分页对象、所属部门ID号，分页查询用户列表
	 * @param page
	 * @param orgId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<User> searchUser(final Page<User> page, Long orgId) {
		Org org = new Org(orgId);
		
		String hql = "from User user where user.org=?";
		return userDao.findPage(page, hql, org);
	}
	
	/**
	 * 查询所有记录
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<User> getAll() {
		return userDao.getAll();
	}
	
	/**
	 * 根据用户ID查询该用户所拥有的权限列表
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<String> getAuthoritiesName(Long userId) {
		String sql = "select a.name from sec_user u " + 
					" left outer join sec_role_user ru on u.id=ru.user_id " + 
					" left outer join sec_role r on ru.role_id=r.id " + 
					" left outer join sec_role_authority ra on r.id = ra.role_id " + 
					" left outer join sec_authority a on ra.authority_id = a.id " +                     
					" where u.id=? ";
		SQLQuery query = userDao.createSQLQuery(sql, userId);
		return query.list();
	}
	
	/**
	 * 根据用户ID查询该用户所拥有的角色列表
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<String> getRolesName(Long userId) {
		String sql = "select r.name from sec_user u " + 
					" left outer join sec_role_user ru on u.id=ru.user_id " + 
					" left outer join sec_role r on ru.role_id=r.id " + 
					" where u.id=? ";
		SQLQuery query = userDao.createSQLQuery(sql, userId);
		return query.list();
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(EncodeUtils.hexEncode(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(EncodeUtils.hexEncode(hashPassword));
	}

}
