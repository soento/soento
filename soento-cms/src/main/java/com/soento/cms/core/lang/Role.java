package com.soento.cms.core.lang;

import com.soento.core.lang.BaseObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseObject {
    private String name;
    private String text;
    private String parent;
    private String icon;
}
