package org.jeapf.framework.security.dao;

import org.jeapf.framework.orm.hibernate.HibernateDao;
import org.jeapf.framework.security.entity.Org;
import org.springframework.stereotype.Component;

/**
 * 部门持久化类
 * @author yuqs
 */
@Component
public class OrgDao extends HibernateDao<Org, Long> {

}
