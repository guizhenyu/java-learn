package io.guizhenyu.transaction.test.service.service;

import io.guizhenyu.transaction.test.service.repository.mapper.CmAccountMapper;
import io.guizhenyu.transaction.test.service.repository.model.CmAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * description: BService date: 2022/2/12 12:12 下午
 *
 * @author: guizhenyu
 */
@Service
public class BService {


  @Autowired
  private CmAccountMapper accountMapper;

//  @Transactional(rollbackFor = Exception.class)
  public void insert(CmAccount account){
    accountMapper.insert(account);
//    System.out.println(1/0);
  }
  @Transactional(rollbackFor = Exception.class,propagation = Propagation.NESTED)
  public void delete(CmAccount cmAccount) {
    accountMapper.delete(cmAccount);
    int i = 1/0;
  }
}
