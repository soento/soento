package com.soento.cms.common.service;

import com.soento.cms.common.pojo.dto.LoginDto;
import com.soento.cms.core.lang.AuthModel;
import com.soento.core.lang.LoginUser;

public interface CommonService {
    LoginUser doLogin(LoginDto req);

    AuthModel getUserAuth(LoginUser user);
}
