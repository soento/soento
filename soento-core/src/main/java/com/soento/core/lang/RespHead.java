package com.soento.core.lang;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soento.core.enums.DateFormat;
import com.soento.core.exception.ServiceException;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.ParseException;
import java.util.Date;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RespHead extends BaseObject {
    /**
     * 应答返回码
     */
    private String code;
    /**
     * 应答描述
     */
    private String desc;
    /**
     * 应答时间，格式：DateFormat.YYYYMMDDHHMISSMS_DASH("yyyy-MM-dd HH:mm:ss SSS")
     */
    private String datetime;

    public void setRespDateTime(Date datetime) {
        this.datetime = DateFormat.YYYYMMDDHHMISSMS_DASH.instance().format(datetime);
    }

    @JsonIgnore
    public Date getRespDateTime() {
        try {
            return DateFormat.YYYYMMDDHHMISSMS_DASH.instance().parse(this.datetime);
        } catch (ParseException e) {
            throw new ServiceException(e);
        }
    }

    public static RespHead.RespHeadBuilder builder() {
        return new RespHead.RespHeadBuilder();
    }

    public static class RespHeadBuilder {
        private String code;
        private String desc;

        RespHeadBuilder() {
        }

        public RespHead.RespHeadBuilder code(String code) {
            this.code = code;
            return this;
        }

        public RespHead.RespHeadBuilder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public RespHead build() {
            RespHead head = new RespHead();
            head.setCode(this.code);
            head.setDesc(this.desc);
            head.setRespDateTime(new Date());
            return head;
        }
    }
}
