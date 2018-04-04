package com.juneyao.demo.web.pojo.entity;

import com.soento.core.lang.BaseEntity;
import lombok.Data;

/**
 * @author yantao.zeng
 */
@Data
public class TestEntity extends BaseEntity {
    public final static String SEQUENCE = "SEQ_TEST";
    private String code;
    private String codeName;
    private String value;
}
