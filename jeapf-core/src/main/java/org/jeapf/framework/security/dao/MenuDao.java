package org.jeapf.framework.security.dao;

import org.jeapf.framework.orm.hibernate.HibernateDao;
import org.jeapf.framework.security.entity.Menu;
import org.springframework.stereotype.Component;

/**
 * 菜单持久化类
 * @author yuqs
 */
@Component
public class MenuDao extends HibernateDao<Menu, Long> {

}
