package service;

import dao.CustomerDao;
import domain.Customer;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CustomerServiceImpl implements CustomerService {


    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    //保存客户
    @Override
    public void save(Customer customer) {
        System.out.println("业务层：保存客户");
        customerDao.save(customer);
    }

    @Override
    public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        return customerDao.findByPage(pageCode, pageSize, criteria);
    }

    //通过主键查找客户
    @Override
    public Customer findById(Long cust_id) {
        return customerDao.findById(cust_id);
    }

    //删除客户
    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    //修改客户
    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }

    //查找全部
    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    //统计来源客户的数量
    @Override
    public List<Object[]> findBySource() {
        return customerDao.findBySource();
    }


}
