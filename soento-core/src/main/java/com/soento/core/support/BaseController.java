package com.soento.core.support;

import com.soento.core.consts.SystemCode;
import com.soento.core.lang.Resp;
import com.soento.core.lang.RespHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author soento
 */
public class BaseController {
    @Autowired
    protected MessageSourceAccessor msa;

    /**
     * 获取HttpServletRequest对象
     *
     * @return HttpServletRequest对象
     */
    protected HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getRequest();
        }
        return null;
    }

    /**
     * 获取HttpServletResponse对象
     *
     * @return HttpServletResponse对象
     */
    protected HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getResponse();
        }
        return null;
    }

    /**
     * 获取HttpSession对象
     *
     * @return HttpSession对象
     */
    protected HttpSession getSession() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            return request.getSession();
        }
        return null;
    }

    /**
     * 获取Locale对象
     *
     * @return Locale对象
     */
    protected Locale getLocale() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            return request.getLocale();
        }
        return null;
    }

    protected Resp success() {
        RespHead head = RespHead.builder()
                .code(SystemCode.SUCCESS)
                .desc(msa.getMessage(SystemCode.SUCCESS))
                .build();
        return Resp.builder().head(head).build();
    }

    protected <T> Resp success(T body) {
        RespHead head = RespHead.builder()
                .code(SystemCode.SUCCESS)
                .desc(msa.getMessage(SystemCode.SUCCESS))
                .build();
        return Resp.builder().head(head).body(body).build();
    }

    protected Resp error(String code) {
        RespHead head = RespHead.builder()
                .code(code)
                .desc(msa.getMessage(code))
                .build();
        return Resp.builder().head(head).build();
    }

    protected Resp error(String code, String... args) {
        RespHead head = RespHead.builder()
                .code(code)
                .desc(msa.getMessage(code, args))
                .build();
        return Resp.builder().head(head).build();
    }

    protected static BaseController.ModelAndViewBuilder builder() {
        return new BaseController.ModelAndViewBuilder();
    }

    protected static class ModelAndViewBuilder {
        private String view;
        private Map<String, Object> model;

        ModelAndViewBuilder() {

        }

        public BaseController.ModelAndViewBuilder view(String view) {
            this.view = view;
            return this;
        }

        public BaseController.ModelAndViewBuilder addObject(String key, Object value) {
            if (model == null) {
                model = new HashMap<>(16);
            }
            model.put(key, value);
            return this;
        }

        public ModelAndView build() {
            ModelAndView result = new ModelAndView(this.view);
            result.addAllObjects(this.model);
            return result;
        }
    }
}
