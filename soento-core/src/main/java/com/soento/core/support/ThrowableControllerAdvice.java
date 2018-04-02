package com.soento.core.support;

import com.soento.core.consts.SystemCode;
import com.soento.core.exception.ClientException;
import com.soento.core.exception.ServiceException;
import com.soento.core.lang.Message;
import com.soento.core.lang.Resp;
import com.soento.core.lang.RespHead;
import com.soento.core.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author soento
 */
@Slf4j
@ControllerAdvice
public class ThrowableControllerAdvice {

    @Autowired
    protected MessageSourceAccessor msa;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleBindException(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException cause) {
        // 输出错误信息
        log.info("== 参数绑定异常 ==");
        process(request, response, convert(cause.getBindingResult().getAllErrors(), cause));
    }

    @ExceptionHandler(BindException.class)
    public void handleBindException(HttpServletRequest request, HttpServletResponse response, BindException cause) {
        // 输出错误信息
        log.info("== 请求参数异常 ==");
        process(request, response, convert(cause.getAllErrors(), cause));
    }

    private Resp convert(List<ObjectError> errors, Throwable cause) {
        StringBuilder msg = null;
        for (ObjectError error : errors) {
            msg = new StringBuilder();
            if (error instanceof FieldError) {
                msg.append("【" + ((FieldError) error).getField() + "】");
            }
            msg.append(error.getCode());
            msg.append(":");
            msg.append(error.getDefaultMessage());
            log.info(msg.toString());
        }
        log.info(cause.getMessage(), cause);
        // 构造返回值
        Message message = Message.builder()
                .code(String.valueOf(HttpServletResponse.SC_BAD_REQUEST))
                .content("参数错误")
                .build();
        RespHead head = RespHead.builder()
                .code(message.getCode())
                .desc(message.getContent())
                .build();
        return Resp.builder().head(head).build();
    }

    @ExceptionHandler(ClientException.class)
    public void handleClientException(HttpServletRequest request, HttpServletResponse response, ClientException cause) {
        // 输出错误信息
        log.info("== 前端业务异常 ==");
        log.info(cause.getMessage(), cause);
        // 构造返回值
        RespHead head = RespHead.builder()
                .code(cause.message().getCode())
                .desc(cause.message().getContent())
                .build();
        Resp resp = Resp.builder().head(head).build();
        process(request, response, resp);
    }

    @ExceptionHandler(ServiceException.class)
    public void handleServiceException(HttpServletRequest request, HttpServletResponse response, ServiceException cause) {
        // 输出错误信息
        log.warn("== 后端业务异常 ==");
        log.warn(cause.getMessage(), cause);
        // 构造返回值
        Message message = Message.builder().code(SystemCode.E9998).content(msa.getMessage(SystemCode.E9998)).build();
        RespHead head = RespHead.builder()
                .code(message.getCode())
                .desc(message.getContent())
                .build();
        Resp resp = Resp.builder().head(head).build();
        process(request, response, resp);
    }

    @ExceptionHandler({Error.class, Exception.class, Throwable.class})
    public void handlerOthers(HttpServletRequest request, HttpServletResponse response, Throwable cause) {
        // 输出错误信息
        log.error("== 未知系统异常 ==");
        log.error(cause.getMessage(), cause);
        // 构造返回值
        Message message = Message.builder().code(SystemCode.E9999).content(msa.getMessage(SystemCode.E9999)).build();
        RespHead head = RespHead.builder()
                .code(message.getCode())
                .desc(message.getContent())
                .build();
        Resp resp = Resp.builder().head(head).build();
        process(request, response, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response, Resp resp) {
        try {
            //判断是否为ajax请求，默认不是
            if (HttpUtil.isAjax(request)) {
                PrintWriter writer = response.getWriter();
                writer.write(resp.toJson());
                writer.close();
                response.flushBuffer();
                return;
            }
            response.sendRedirect("/500");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}