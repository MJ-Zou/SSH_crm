package service;

import domain.PageBean;
import domain.Visit;
import org.hibernate.criterion.DetachedCriteria;

public interface VisitService  {
    PageBean<Visit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

    void save(Visit visit);
}
