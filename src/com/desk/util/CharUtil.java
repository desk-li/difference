package com.desk.util;

/**
 * @author CP_lixiaolong
 * @date 2020/12/2 14:45
 */
public class CharUtil {

    /**
     *
     * 将字符串的首字母转大写
     * @param str
     * @author CP_lixiaolong
     * @date 2020/12/2 14:45
     * @return java.lang.String
     */
    public static String captureName(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs=str.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

}
