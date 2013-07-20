package org.jeapf.framework.security.dao;

import org.jeapf.framework.orm.hibernate.HibernateDao;
import org.jeapf.framework.security.entity.Resource;
import org.springframework.stereotype.Component;

/**
 * 资源持久化类
 * @author yuqs
 */
@Component
public class ResourceDao extends HibernateDao<Resource, Long> {

}
