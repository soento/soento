package com.soento.core.exception;

import com.soento.core.lang.Message;
import com.soento.core.support.MessageSourceAccessor;

/**
 * @author soento
 */
public class ClientException extends ServiceException {
    public ClientException() {
        super();
    }

    public ClientException(Message message) {
        super(message);
    }

    public ClientException(Message message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    public static ClientException build(String code, String content) {
        return new ClientException(Message.builder().code(code).content(content).build());
    }

    public static ClientException build(MessageSourceAccessor msa, String code, String... args) {
        return new ClientException(Message.builder().code(code).content(msa.getMessage(code, args)).build());
    }

    public static ClientException build(Throwable cause, String code, String content) {
        return new ClientException(Message.builder().code(code).content(content).build(), cause);
    }

    public static ClientException build(Throwable cause, MessageSourceAccessor msa, String code, String... args) {
        return new ClientException(Message.builder().code(code).content(msa.getMessage(code, args)).build(), cause);
    }
}
