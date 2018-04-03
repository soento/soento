package com.soento.core.lang;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Privilege extends BaseObject {
    public final static String ROOT = "ROOT";
    private String name;
    private String text;
    private String parent;
    private String type;
    private String uri;
    private String icon;
    private Integer sort;
}
