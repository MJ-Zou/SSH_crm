package dao;

import domain.Customer;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 持久层
 */
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
    /**
     * 统计客户的来源
     * @return
     */
    @Override
    public List<Object[]> findBySource() {
        //定义HQL
        //SELECT * FROM cst_customer c, base_dict d WHERE d.dict_id=c.`cust_source`
        //分组查询 SELECT * FROM cst_customer c, base_dict d WHERE d.dict_id=c.`cust_source` GROUP BY d.`dict_id`;
        //查询内容 SELECT d.`dict_item_name`,COUNT(*) FROM cst_customer c, base_dict d WHERE d.dict_id=c.`cust_source` GROUP BY d.`dict_id`;

        String hql="select c.source.dict_item_name,count(*) from Customer c inner join c.source group by c.source";
        //查询
        return (List<Object[]>) this.getHibernateTemplate().find(hql);
    }

/*
    //保存客户
    @Override
    public void save(Customer customer) {
        System.out.println("持久层：保存客户");
        //把数据存到数据库中
        this.getHibernateTemplate().save(customer);
    }
*/
/*
    //分页查询
    @Override
    public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        PageBean<Customer> page = new PageBean<Customer>();
        //当前页
        page.setPageCode(pageCode);
        //每页记录数
        page.setPageSize(pageSize);

        criteria.setProjection(Projections.rowCount());
        List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
        if (list != null && list.size() > 0) {
            int totalCount = list.get(0).intValue();
            //总记录数
            page.setTotalCount(totalCount);
        }

        criteria.setProjection(null);
        //每页显示的数据
        List<Customer> beanList = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode - 1) * pageSize, pageSize);
        page.setBeanList(beanList);

        return page;
    }
*/
/*
    //通过主键查找客户
    @Override
    public Customer findById(Long cust_id) {
        return this.getHibernateTemplate().get(Customer.class, cust_id);
    }
*/
/*
    //删除客户
    @Override
    public void delete(Customer customer) {
        this.getHibernateTemplate().delete(customer);
    }
*/
/*
    //修改客户
    @Override
    public void update(Customer customer) {
        this.getHibernateTemplate().update(customer);
    }
*/
}
