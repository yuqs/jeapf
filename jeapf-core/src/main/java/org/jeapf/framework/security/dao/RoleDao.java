package org.jeapf.framework.security.dao;

import org.jeapf.framework.orm.hibernate.HibernateDao;
import org.jeapf.framework.security.entity.Role;
import org.springframework.stereotype.Component;

/**
 * 角色持久化类
 * @author yuqs
 */
@Component
public class RoleDao extends HibernateDao<Role, Long> {

}
