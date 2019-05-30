package dao;

import domain.Dict;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 持久层
 */
public class DictDaoImpl extends HibernateDaoSupport implements DictDao  {
    @Override
    public List<Dict> findByCode(String dict_type_code) {
        return (List<Dict>) this.getHibernateTemplate().find(
                "from Dict where dict_type_code=?",dict_type_code);
    }
}
