package com.juneyao.demo.web.service;

import com.juneyao.demo.web.dao.TestDao;
import com.juneyao.demo.web.pojo.entity.TestEntity;
import com.juneyao.demo.web.pojo.query.TestQuery;
import com.soento.core.support.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TestService extends AbstractService {
    @Autowired
    private TestDao testDao;

    @Transactional(rollbackFor = Throwable.class)
    public Object test() {
        TestEntity iEntity = new TestEntity();
        iEntity.setId(nextseq(TestEntity.SEQUENCE));
        iEntity.setCode("11");
        iEntity.setValue("22");
        iEntity.setCodeName("33");
        Long iCnt = testDao.insert(iEntity);
        log.info("插入件数:{}", iCnt);

        TestEntity uEntity = new TestEntity();
        uEntity.setId(iEntity.getId());
        uEntity.setCode("11--");
        uEntity.setValue("22--");
        uEntity.setCodeName("33--");
        Long uCnt = testDao.update(uEntity);
        log.info("修改件数:{}", uCnt);

        Long dCnt = testDao.delete("111");
        log.info("删除件数:{}", dCnt);

        TestQuery query = new TestQuery();
        return testDao.select(query);
    }
}
