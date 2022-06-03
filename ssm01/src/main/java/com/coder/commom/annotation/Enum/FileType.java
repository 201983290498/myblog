package com.coder.commom.annotation.Enum;

/**
 * @author coder
 */

public enum FileType {

    /**
     * 普通文件
     */
    FILE("普通文件"),

    /**
     * 二进制文件
     */
    BINARY("二进制文件"),

    /**
     * 目录
     */
    CONTENT("目录");

    private final String type;
    FileType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static FileType getFileType(String type) {
        if("普通文件".equals(type)) {
            return FILE;
        } else if("二进制文件".equals(type)) {
            return BINARY;
        } else {
            return CONTENT;
        }
    }
}
