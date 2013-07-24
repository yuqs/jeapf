package org.jeapf.framework.config.dao;

import org.jeapf.framework.config.entity.ConfigItem;
import org.jeapf.framework.orm.hibernate.HibernateDao;
import org.springframework.stereotype.Component;

/**
 * 配置元素持久化类
 * @author yuqs
 */
@Component
public class ConfigItemDao extends HibernateDao<ConfigItem, Long> {

}
