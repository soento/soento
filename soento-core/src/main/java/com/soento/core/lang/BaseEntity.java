package com.soento.core.lang;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity extends BaseObject {
    protected String id;
    protected String createBy;
    protected Date createDate;
    protected String modifyBy;
    protected Date modifyDate;
    protected Integer deleteFlag;
}
