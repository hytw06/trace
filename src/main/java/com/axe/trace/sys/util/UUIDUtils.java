package com.axe.trace.sys.util;

import java.util.UUID;

/**
 * UUID工具类
 */
public class UUIDUtils {

    /**
     * 基于指定的关键字符串生成UUID
     *
     * @param key 关键字符串
     * @return 唯一性字符串
     */
    public static String getUUID(String key) {
        if (StringUtils.isBlank(key)) {
            return getUUID();
        }
        return UUID.nameUUIDFromBytes(key.getBytes()).toString().replaceAll("-", "");
    }

    /**
     * 伪随机数生成器生成UUID
     *
     * @return 唯一性字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
