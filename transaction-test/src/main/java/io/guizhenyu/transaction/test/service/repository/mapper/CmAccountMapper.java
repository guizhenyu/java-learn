package io.guizhenyu.transaction.test.service.repository.mapper;

import io.guizhenyu.transaction.test.service.mybatis.basemapper.CommonMapper;
import io.guizhenyu.transaction.test.service.repository.model.CmAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface CmAccountMapper extends CommonMapper<CmAccount> {
}