package io.guizhenyu.transaction.test.service.mybatis.basemapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author zhanghg
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T>, InsertListMapper<T> {

}
