package com.wsi.sdk.util;

/**
 * Created by Ievgen Usenko
 * Date: 6/24/14.
 */
public class StringUtils {

    public static boolean isBlank(final String text) {
        return text == null || "".equals(text.trim());
    }
}
