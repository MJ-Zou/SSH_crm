package service;

import dao.VisitDao;
import dao.VisitDaoImpl;
import domain.PageBean;
import domain.Visit;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service(value = "visitService")
@Transactional
public class VisitServiceImpl implements VisitService {

    @Resource(name = "visitDao")
    private VisitDao visitDao;


    //分页查询
    @Override
    public PageBean<Visit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        return visitDao.findByPage(pageCode, pageSize, criteria);
    }

    //保存拜访记录
    @Override
    public void save(Visit visit) {
        visitDao.save(visit);
    }
}
