package com.juneyao.demo.web.service;

import com.juneyao.demo.web.mapper.TestMapper;
import com.soento.core.support.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService extends AbstractService {
    @Autowired
    private TestMapper testMapper;

    public Object getAll() {
        return testMapper.getAll();
    }
}
