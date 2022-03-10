package io.guizhenyu.transaction.test.service.mybatis.service;

import java.util.List;

/**
 * @author zhanghg
 */
public interface BaseService<T> {

  T selectByKey(Object key);

  @Deprecated
  int save(T entity);

  int saveSelective(T entity);

  int delete(Object key);

  int deleteByCondition(T entity);

  @Deprecated
  int updateAll(T entity);

  int updateNotNull(T entity);

  int insertList(List<T> list);

  List<T> selectByExample(Object example);

  List<T> select(T entity);

  T selectOne(T entity);

}
