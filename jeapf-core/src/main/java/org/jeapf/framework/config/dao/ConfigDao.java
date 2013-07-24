package org.jeapf.framework.config.dao;

import org.jeapf.framework.config.entity.Config;
import org.jeapf.framework.orm.hibernate.HibernateDao;
import org.springframework.stereotype.Component;

/**
 * 配置持久化类
 * @author yuqs
 */
@Component
public class ConfigDao extends HibernateDao<Config, Long> {

}
