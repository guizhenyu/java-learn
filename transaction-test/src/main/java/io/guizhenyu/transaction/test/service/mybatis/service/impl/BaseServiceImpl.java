package io.guizhenyu.transaction.test.service.mybatis.service.impl;

import io.guizhenyu.transaction.test.service.mybatis.basemapper.CommonMapper;
import io.guizhenyu.transaction.test.service.mybatis.service.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhanghg
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

  @Autowired
  protected CommonMapper<T> mapper;

  public CommonMapper<T> getMapper() {
    return mapper;
  }

  @Override
  public T selectByKey(Object key) {
    return mapper.selectByPrimaryKey(key);
  }

  @Override
  public int save(T entity) {
    return mapper.insert(entity);
  }

  @Override
  public int saveSelective(T entity) {
    return mapper.insertSelective(entity);
  }

  @Override
  public int delete(Object key) {
    return mapper.deleteByPrimaryKey(key);
  }

  @Override
  public int deleteByCondition(T entity) {
    return mapper.delete(entity);
  }

  @Override
  public int updateAll(T entity) {
    return mapper.updateByPrimaryKey(entity);
  }

  @Override
  public int updateNotNull(T entity) {
    return mapper.updateByPrimaryKeySelective(entity);
  }

  @Override
  public List<T> selectByExample(Object example) {
    return mapper.selectByExample(example);
  }

  @Override
  public List<T> select(T entity) {
    return mapper.select(entity);
  }

  @Override
  public T selectOne(T entity) {
    return mapper.selectOne(entity);
  }

  @Override
  public int insertList(List<T> list) {
    return mapper.insertList(list);
  }

}


