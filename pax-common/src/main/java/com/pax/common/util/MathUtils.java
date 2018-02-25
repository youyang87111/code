package com.pax.common.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/27
 * @description:
 */
@SuppressWarnings("ALL")
public class MathUtils {
    private static final Logger logger = LoggerFactory.getLogger(MathUtils.class);

    public static String multiply(String src, int multiple, int decimal) {
        try {
            if (StringUtils.isNotBlank(src)) {
                BigDecimal bigDecimal = new BigDecimal(src)
                        .multiply(new BigDecimal(multiple))
                        .setScale(decimal, BigDecimal.ROUND_HALF_UP);
                return bigDecimal.toPlainString();
            }
        } catch (Exception e) {
            logger.info("单位转换出错,原因:[{}]", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public static String divide(String src, int multiple, int decimal) {
        try {
            if (StringUtils.isNotBlank(src)) {
                double result = new BigDecimal(src).divide(new BigDecimal(multiple), decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
                return String.valueOf(result);
            }
        } catch (Exception e) {
            logger.info("单位转换出错,原因:[{}]", e.getMessage());
            throw new RuntimeException(e.getMessage());


        }
        return null;
    }

    public static String add(String src1, String src2, int decimal) {
        try {
            if (StringUtils.isNotBlank(src1) && StringUtils.isNotBlank(src2)) {
                BigDecimal bigDecimal = new BigDecimal(src1)
                        .add(new BigDecimal(src2))
                        .setScale(decimal, BigDecimal.ROUND_HALF_UP);
                return bigDecimal.toPlainString();
            }
        } catch (Exception e) {
            logger.info("单位转换出错,原因:[{}]", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public static String subtract(String src1, String src2, int decimal) {
        try {
            if (StringUtils.isNotBlank(src1) && StringUtils.isNotBlank(src2)) {
                BigDecimal bigDecimal = new BigDecimal(src1)
                        .subtract(new BigDecimal(src2))
                        .setScale(decimal, BigDecimal.ROUND_HALF_UP);
                return bigDecimal.toPlainString();
            }
        } catch (Exception e) {
            logger.info("单位转换出错,原因:[{}]", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public static String subtractAsAbs(String src1, String src2, int decimal) {
        try {
            if (StringUtils.isNotBlank(src1) && StringUtils.isNotBlank(src2)) {
                BigDecimal bigDecimal = new BigDecimal(src1)
                        .subtract(new BigDecimal(src2))
                        .setScale(decimal, BigDecimal.ROUND_HALF_UP);
                return bigDecimal.abs().toPlainString();
            }
        } catch (Exception e) {
            logger.info("单位转换出错,原因:[{}]", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
}
