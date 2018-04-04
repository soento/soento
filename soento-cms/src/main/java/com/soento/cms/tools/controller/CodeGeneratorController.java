package com.soento.cms.tools.controller;

import com.soento.cms.core.support.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yantao.zeng
 */
@Slf4j
@Controller
@RequestMapping("tools")
public class CodeGeneratorController extends BaseController {
    @RequestMapping("codeGenerator")
    public ModelAndView login() {
        return template("tools/codeGenerator");
    }

    @RequestMapping("codeGenerator/generator")
    public void generator(HttpServletResponse response) {
    }
}
