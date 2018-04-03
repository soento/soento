package com.juneyao.demo.web.controller;

import com.juneyao.demo.web.service.T;
import com.soento.core.annotation.MethodLog;
import com.soento.core.consts.SystemCode;
import com.soento.core.handler.RedisHandler;
import com.soento.core.handler.SpringHandler;
import com.soento.core.support.AbstractController;
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
    private T testService;

    @RequestMapping("/")
    public ModelAndView index() {
        return builder().view("index").addObject("key", "value").build();
    }

    @RequestMapping("test")
    @ResponseBody
    @MethodLog("测试")
    public Object test() {
        String msg = msa.getMessage(SystemCode.SUCCESS);
        log.info(msg);
        log.info(SpringHandler.getValue("mybatis.mapper-locations"));
        log.info((String) RedisHandler.get("aa"));
        RedisHandler.set("aa", "bbbbb");
        log.info((String) RedisHandler.get("aa"));
        log.info(testService.nextseq("SEQ_TEST"));
        log.info(testService.nextseq("SEQ_TEST", "CTTT"));
        return success(testService.getAll());
    }
}
