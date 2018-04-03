package com.soento.cms.core.lang;

import com.soento.core.lang.BaseObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 菜单信息
 *
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Menu extends BaseObject {
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单显示文本
     */
    private String text;
    /**
     * 上一级菜单名称
     */
    private String parent;
    /**
     * 图标样式
     */
    private String icon;
    /**
     * 点击链接
     */
    private String link;
    /**
     * 活动样式
     */
    private String active;
    /**
     * 下一级菜单
     */
    private List<Menu> children;
}
