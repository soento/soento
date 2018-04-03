package com.juneyao.demo.web.entity;

import com.soento.core.lang.BaseObject;
import lombok.Data;

/**
 * @author yantao.zeng
 */
@Data
public class TestEntity extends BaseObject {
    private Integer id;
    private String code;
    private String codeName;
    private String value;
}
