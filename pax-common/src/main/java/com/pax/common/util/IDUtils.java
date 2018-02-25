package com.pax.common.util;

/**
 * Created by zhuxl@paxsz.com on 2016/7/12.
 */
public class IDUtils {
    public static final String supplementId(String id, int length) {
        String result = "";
        char[] chars = id.toCharArray();
        int len = chars.length;
        int count = length - len;
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                result += "0";
            }
            result += id;
        } else {
            result = id;
        }
        return result;
    }

}
