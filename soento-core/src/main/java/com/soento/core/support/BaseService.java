package com.soento.core.support;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yantao.zeng
 */
public class BaseService {
    @Autowired
    protected MessageSourceAccessor msa;
}
