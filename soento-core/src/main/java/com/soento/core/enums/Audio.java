package com.soento.core.enums;

/**
 * 音频类型
 *
 * @author soento
 */
public enum Audio {
    /**
     * mp3
     */
    MP3(".mp3");

    private String value;

    Audio(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean has(String value) {
        if (Audio.MP3.getValue().equalsIgnoreCase(value)) {
            return true;
        }
        return false;
    }
}
