package cn.featherfly.common.location;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zj on 2017/4/10.
 */
public class UnicodeUtils {

    private static final Pattern PATTERN = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

    /**
     * string2Unicode
     * @param string string
     * @return unicode_string
     */
    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * unicode2String
     * @param unicode unicode_string
     * @return string
     */
    public static String unicodeToString(String unicode) {
        Matcher matcher = PATTERN.matcher(unicode);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            unicode = unicode.replace(matcher.group(1), ch + "");
        }
        return unicode;
    }

}
