package com.soento.core.lang;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Req<T> extends BaseObject {
    /**
     * 请求头
     */
    private ReqHead head;
    /**
     * 请求体
     */
    private T body;
}
