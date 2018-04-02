package com.juneyao.demo.web.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.soento.core.lang.BaseObject;
import lombok.Data;

/**
 * @author yantao.zeng
 */
@Data
@TableName("test")
public class TestEntity extends BaseObject {
    private Integer id;
    private String code;
    private String value;
}
