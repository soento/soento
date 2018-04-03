package com.soento.cms.common.controller;

import com.soento.cms.common.bo.LoginReq;
import com.soento.cms.common.service.CommonService;
import com.soento.cms.core.lang.AuthModel;
import com.soento.cms.core.support.BaseController;
import com.soento.core.annotation.Auth;
import com.soento.core.consts.AuthType;
import com.soento.core.lang.LoginUser;
import com.soento.core.lang.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author soento
 */
@Slf4j
@Controller
public class CommonController extends BaseController {
    @Autowired
    private CommonService commonService;

    @RequestMapping("login")
    public ModelAndView login() {
        return result("login", "Login", null, plugin().ctrl(true));
    }

    @RequestMapping("doLogin")
    @ResponseBody
    public Resp doLogin(@Valid @RequestBody LoginReq req) {
        LoginUser user = commonService.doLogin(req);
        setUser(user);
        AuthModel auth = commonService.getUserAuth(user);
        setAuthModel(auth);
        return success();
    }

    @RequestMapping("index")
    @Auth(text = "欢迎页", type = AuthType.PAGE)
    public ModelAndView index() {
        return template("Index");
    }

    @RequestMapping("doLogout")
    @ResponseBody
    @Auth(text = "注销操作")
    public Resp doLogout() {
        getSession().invalidate();
        return success();
    }
}
