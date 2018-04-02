package com.soento.core.support;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yantao.zeng
 */
public abstract class AbstractService {
    @Autowired
    protected MessageSourceAccessor msa;
}
