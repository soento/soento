package com.soento.cms.common.service;

import com.soento.cms.common.bo.LoginReq;
import com.soento.cms.core.lang.AuthModel;
import com.soento.core.lang.LoginUser;

public interface CommonService {
    LoginUser doLogin(LoginReq req);

    AuthModel getUserAuth(LoginUser user);
}
