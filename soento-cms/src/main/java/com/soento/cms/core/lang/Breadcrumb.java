package com.soento.cms.core.lang;

import com.soento.core.lang.BaseObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 面包屑
 *
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Breadcrumb extends BaseObject {
    /**
     * 图标样式
     */
    private String icon;
    /**
     * 点击链接
     */
    private String link;
    /**
     * 显示文本
     */
    private String text;
}
