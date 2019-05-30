package service;

import dao.DictDao;
import domain.Dict;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务层
 */
@Transactional
public class DictServiceImpl implements DictService {
    private DictDao dictDao;

    public void setDictDao(DictDao dictDao) {
        this.dictDao = dictDao;
    }

    //通过字段的type_code查询客户级别或者客户的来源
    @Override
    public List<Dict> findByCode(String dict_type_code) {
        return dictDao. findByCode(dict_type_code) ;
    }
}
