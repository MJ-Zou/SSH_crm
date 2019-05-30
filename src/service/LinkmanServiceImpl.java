package service;

import dao.LinkmanDao;
import domain.Linkman;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public class LinkmanServiceImpl implements LinkmanService {
    LinkmanDao linkmanDao;

    public void setLinkmanDao(LinkmanDao linkmanDao) {
        this.linkmanDao = linkmanDao;
    }

    @Override
    public PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        return linkmanDao.findByPage(pageCode,pageSize,criteria);
    }
}
