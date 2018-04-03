package com.juneyao.demo.web.controller;

import com.juneyao.demo.web.mapper.TestMapper;
import com.soento.core.consts.SystemCode;
import com.soento.core.handler.RedisHandler;
import com.soento.core.support.AbstractController;
import com.soento.core.handler.SpringHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class TestController extends AbstractController {

    @Autowired
    private TestMapper testMapper;

    @RequestMapping("/")
    public ModelAndView index() {
        return builder().view("index").addObject("key", "value").build();
    }

    @RequestMapping("test")
    @ResponseBody
    public Object test() {
        String msg = msa.getMessage(SystemCode.SUCCESS);
        log.info(msg);
        log.info(SpringHandler.getValue("mybatis.mapper-locations"));
        log.info((String)RedisHandler.get("aa"));
        RedisHandler.set("aa", "bbbbb");
        log.info((String)RedisHandler.get("aa"));
        return success(testMapper.getAll());
    }
}
