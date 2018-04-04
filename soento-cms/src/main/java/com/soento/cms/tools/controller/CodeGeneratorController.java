package com.soento.cms.tools.controller;

import com.soento.cms.core.support.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yantao.zeng
 */
@Slf4j
@Controller
@RequestMapping("/tools/codeGenerator")
public class CodeGeneratorController extends BaseController {
    @RequestMapping("")
    public ModelAndView login() {
        return result("login", "Login", null);
    }
}
