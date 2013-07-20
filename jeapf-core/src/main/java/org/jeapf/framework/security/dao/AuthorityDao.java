package org.jeapf.framework.security.dao;

import org.jeapf.framework.orm.hibernate.HibernateDao;
import org.jeapf.framework.security.entity.Authority;
import org.springframework.stereotype.Component;

/**
 * 权限持久化类
 * @author yuqs
 */
@Component
public class AuthorityDao extends HibernateDao<Authority, Long> {

}
