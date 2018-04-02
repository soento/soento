package com.soento.core.enums;

/**
 * 视频类型
 *
 * @author soento
 */
public enum Video {
    /**
     * mp4
     */
    MP4(".mp4");
    private String value;

    Video(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean has(String extension) {
        if (Video.MP4.getValue().equalsIgnoreCase(extension)) {
            return true;
        }
        return false;
    }
}