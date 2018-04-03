package com.soento.cms.common.bo;

import com.soento.core.lang.BaseObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginReq extends BaseObject {
    @NotBlank(message = "{username.not.blank}")
    private String username;
    @NotBlank(message = "{password.not.blank}")
    private String password;
}
