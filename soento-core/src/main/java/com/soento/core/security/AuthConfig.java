package com.soento.core.security;

import com.soento.core.lang.BaseObject;
import com.soento.core.lang.Privilege;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class AuthConfig extends BaseObject {
    private List<String> whitelist = new ArrayList<>();
    private List<Privilege> authList = new ArrayList<>();

    @Value("${auth.config.welcome:/}")
    private String welcome;
    @Value("${auth.config.home:/index}")
    private String home;
    @Value("${auth.config.login:/login}")
    private String login;
    @Value("${auth.config.doLogin:/doLogin}")
    private String doLogin;
    @Value("${auth.config.unauthorized:/401}")
    private String unauthorized;
    @Value("${auth.config.source:/assets/}")
    private String source;
}
