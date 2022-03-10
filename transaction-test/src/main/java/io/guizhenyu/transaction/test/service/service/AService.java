package io.guizhenyu.transaction.test.service.service;

import io.guizhenyu.transaction.test.service.repository.mapper.CmAccountMapper;
import io.guizhenyu.transaction.test.service.repository.model.CmAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description: AService date: 2022/2/12 12:11 下午
 *
 * @author: guizhenyu
 */
@Service
public class AService {

  @Autowired
  private CmAccountMapper account;

  @Autowired
  private BService bService;

  @Transactional(rollbackFor = Exception.class)
  public void insert(CmAccount cmAccount){
    try {
      bService.delete(cmAccount);
    }catch (Exception e){
      System.out.println("err");
//      e.printStackTrace();
    }
//    bService.insert(cmAccount);

    account.insert(cmAccount);
    System.out.println("success");
  }


}
