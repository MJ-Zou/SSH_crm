package dao;

import domain.Dict;

import java.util.List;

public interface DictDao {
    List<Dict> findByCode(String dict_type_code);
}
