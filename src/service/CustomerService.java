package service;

import domain.Customer;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {

    public void save(Customer customer);


    PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

    Customer findById(Long cust_id);

    void delete(Customer customer);

    void update(Customer customer);

    List<Customer> findAll();

    List<Object[]> findBySource();
}
