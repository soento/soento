package com.soento.core.config;

import com.soento.core.consts.Constants;
import com.soento.core.lang.BaseObject;
import com.soento.core.util.PathUtil;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author soento
 */
@EqualsAndHashCode(callSuper = false)
@Component
public class SystemConfig extends BaseObject {
    private final static String CLASSPATH = "classpath:/";

    private String domain;

    private String uploadRootDir;

    private String uploadRootUri;

    @Value("${system.domain:http://127.0.0.1:8080}")
    public void setDomain(String domain) {
        this.domain = domain;
        if (!this.domain.endsWith(Constants.SLASH)) {
            this.domain = this.domain + Constants.SLASH;
        }
    }

    @Value("${system.upload.root.dir:classpath:/static/assets}")
    public void setUploadRootDir(String uploadRootDir) {
        this.uploadRootDir = uploadRootDir;
        if (this.uploadRootDir.startsWith(CLASSPATH)) {
            this.uploadRootDir = this.uploadRootDir.replace(CLASSPATH, PathUtil.classpath());
        }
    }

    @Value("${system.upload.root.uri:http://127.0.0.1:8080/assets}")
    public void setUploadRootUri(String uploadRootUri) {
        this.uploadRootUri = uploadRootUri;
        if (!this.uploadRootUri.endsWith(Constants.SLASH)) {
            this.uploadRootUri = this.uploadRootUri + Constants.SLASH;
        }
    }

    public String getDomain() {
        return domain;
    }

    public String getUploadRootDir() {
        return uploadRootDir;
    }

    public String getUploadRootUri() {
        return uploadRootUri;
    }
}
