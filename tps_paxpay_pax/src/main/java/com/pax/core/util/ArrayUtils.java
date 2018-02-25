package com.pax.core.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/5/22
 * @description:
 */
public class ArrayUtils {
    private static final Logger logger = LoggerFactory.getLogger(ArrayUtils.class);

    public static String removeStr(String src, String[] deletes) {
        if (StringUtils.isNotBlank(src) && deletes.length > 0) {
            String res = "";
            List<String> temps = Lists.newArrayList(src.split(","));
            for (String id : deletes) {
                temps.remove(id);
            }
            for (String id : temps) {
                res += "," + id;
            }
            if (res.length() > 0) {
                res = res.substring(1);
                return res;
            } else {
                return null;
            }
        } else {
            return src;
        }
    }
}
