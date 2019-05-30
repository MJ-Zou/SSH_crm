package dao;

import domain.Visit;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository(value = "visitDao")
public class VisitDaoImpl extends BaseDaoImpl<Visit> implements VisitDao{

    @Resource(name = "sessionFactory")
    public void set2SessionFactory(SessionFactory sessionFactory){
        //调用父类的方法
        super.setSessionFactory(sessionFactory);
    }
}
