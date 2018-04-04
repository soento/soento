package com.juneyao.demo.web.service;

import com.juneyao.demo.web.dao.TestDao;
import com.juneyao.demo.web.pojo.bo.TestBo;
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
        startPage(1, 2);
        TestBo query = new TestBo();
        return pageInfo(testDao.select(query));
    }
}
