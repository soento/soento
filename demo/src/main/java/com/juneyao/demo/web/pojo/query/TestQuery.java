package com.juneyao.demo.web.pojo.query;

import com.soento.core.lang.BaseQuery;
import lombok.Data;

@Data
public class TestQuery extends BaseQuery {
    private String code;
    private String codeName;
    private String value;
}
