package org.jeapf.framework.security.dao;

import org.jeapf.framework.orm.hibernate.HibernateDao;
import org.jeapf.framework.security.entity.User;
import org.springframework.stereotype.Component;

/**
 * 用户持久化类
 * @author yuqs
 */
@Component
public class UserDao extends HibernateDao<User, Long> {

}
