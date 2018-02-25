package com.pax.core.util;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/8
 * @description:
 */
public class ConvertUtils {
    public static final char UNDERLINE = '_';

    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            //String.valueOf(Character.toUpperCase(sb.charAt(position)));
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    public static Map camelToUnderline(Map map) {
        if (map == null) {
            return map;
        }
        Set<String> k = map.keySet();
        Set<String> keys= Sets.newHashSet();
        keys.addAll(k);

        Iterator<String> iterator = keys.iterator();
        Map converts = Maps.newHashMap();
        while (iterator.hasNext()) {
            String key = iterator.next();
            converts.put(camelToUnderline(key), map.get(key));
            map.remove(key);
        }
        map.putAll(converts);
        return map;
    }
}
