package com.soento.cms.system.controller;

import com.soento.cms.core.support.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author soento
 */
@Controller
public class ErrorController extends BaseController {

    @RequestMapping("400")
    public ModelAndView error400() {
        if (getUser() != null) {
            return template("400");
        }
        return page("400");
    }

    @RequestMapping("401")
    public ModelAndView error401() {
        if (getUser() != null) {
            return template("401");
        }
        return page("401");
    }

    @RequestMapping("403")
    public ModelAndView error403() {
        if (getUser() != null) {
            return template("403");
        }
        return page("403");
    }

    @RequestMapping("404")
    public ModelAndView error404() {
        if (getUser() != null) {
            return template("404");
        }
        return page("404");
    }

    @RequestMapping("500")
    public ModelAndView error500() {
        if (getUser() != null) {
            return template("500");
        }
        return page("500");
    }

}
