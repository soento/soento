package com.soento.core.lang;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soento.core.enums.DateFormat;
import com.soento.core.exception.ServiceException;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.ParseException;
import java.util.Date;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class ReqHead extends BaseObject {
    /**
     * 访问秘钥
     */
    private String secret;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 设备编号
     */
    private String deviceNo;
    /**
     * 前置流水号
     */
    private String frontSerialNo;
    /**
     * 访问时间，格式：DateFormat.YYYYMMDDHHMISSMS_DASH("yyyy-MM-dd HH:mm:ss SSS")
     */
    private String datetime;

    public void setReqDateTime(Date datetime) {
        this.datetime = DateFormat.YYYYMMDDHHMISSMS_DASH.instance().format(datetime);
    }

    @JsonIgnore
    public Date getReqDateTime() {
        try {
            return DateFormat.YYYYMMDDHHMISSMS_DASH.instance().parse(this.datetime);
        } catch (ParseException e) {
            throw new ServiceException(e);
        }
    }
}
