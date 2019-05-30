package dao;

import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 以后所有DAO接口都继承BaseDao接口
 * 自定义泛型T
 */
public interface BaseDao<T> {
    public void save(T t);

    public void delete(T t);

    public void update(T t);

    public T findById(Long id);

    public List<T> findAll();

    public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
