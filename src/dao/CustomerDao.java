package dao;

import domain.Customer;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerDao extends BaseDao<Customer> {
    List<Object[]> findBySource();


    // public void save(Customer customer);

    // PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

    // Customer findById(Long cust_id);

    // void delete(Customer customer);

    // void update(Customer customer);
}
