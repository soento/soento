package com.soento.core.exception;

import com.soento.core.consts.SystemCode;
import com.soento.core.lang.Message;
import com.soento.core.support.MessageSourceAccessor;

/**
 * @author soento
 */
public class ServiceException extends RuntimeException {
    private Message message;

    public ServiceException() {
        super();
        defaultMessageInfo();
    }

    public ServiceException(Message message) {
        super(message.getMessage());
        this.message = message;
    }

    public ServiceException(Message message, Throwable cause) {
        super(message.getMessage(), cause);
        this.message = message;
    }

    public ServiceException(Throwable cause) {
        super(cause);
        defaultMessageInfo();
    }

    private void defaultMessageInfo() {
        this.message = Message.builder().code(SystemCode.E9998).content("系统繁忙，请稍后重试！").build();
    }

    public static ServiceException build(String code, String content) {
        return new ServiceException(Message.builder().code(code).content(content).build());
    }

    public static ServiceException build(MessageSourceAccessor msa, String code, String... args) {
        return new ServiceException(Message.builder().code(code).content(msa.getMessage(code, args)).build());
    }

    public static ServiceException build(Throwable cause, String code, String content) {
        return new ServiceException(Message.builder().code(code).content(content).build(), cause);
    }

    public static ServiceException build(Throwable cause, MessageSourceAccessor msa, String code, String... args) {
        return new ServiceException(Message.builder().code(code).content(msa.getMessage(code, args)).build(), cause);
    }

    public Message message() {
        return message;
    }
}
