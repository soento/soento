package com.soento.core.lang;

import lombok.*;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseObject {
    private String code;
    private String content;

    public String getMessage() {
        return code + ":" + content;
    }
}
