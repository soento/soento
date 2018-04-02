package com.soento.core.lang;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Resp<T> extends BaseObject {
    /**
     * 响应头
     */
    private RespHead head;
    /**
     * 响应体
     */
    private T body;
}
