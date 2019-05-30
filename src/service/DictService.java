package service;

import domain.Dict;

import java.util.List;

public interface DictService {
    List<Dict> findByCode(String dict_type_code);
}
