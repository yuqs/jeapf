package org.jeapf.framework.dictionary.dao;

import org.jeapf.framework.dictionary.entity.Dictionary;
import org.jeapf.framework.orm.hibernate.HibernateDao;
import org.springframework.stereotype.Component;

/**
 * 配置字典持久化类
 * @author yuqs
 */
@Component
public class DictionaryDao extends HibernateDao<Dictionary, Long> {

}
