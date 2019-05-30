package service;

import domain.Linkman;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface LinkmanService {
    PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
