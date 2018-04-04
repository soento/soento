package com.soento.cms.system.service;

import com.soento.cms.system.pojo.dto.LoginDto;
import com.soento.cms.core.lang.AuthModel;
import com.soento.core.lang.LoginUser;

public interface SystemService {
    LoginUser doLogin(LoginDto req);

    AuthModel getUserAuth(LoginUser user);
}
