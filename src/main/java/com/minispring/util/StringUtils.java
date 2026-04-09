package com.minispring.util;

import java.util.Collection;

/**
 * 字符串工具类
 * 提供常用的字符串操作方法
 */
public class StringUtils {

    /**
     * 空字符串
     */
    public static final String EMPTY_STRING = "";

    /**
     * 判断字符串是否为空或null
     *
     * @param str 要检查的字符串
     * @return 如果字符串为null或空字符串返回true
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 要检查的字符串
     * @return 如果字符串不为null且不是空字符串返回true
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否有实际内容（不是空白字符）
     *
     * @param str 要检查的字符串
     * @return 如果字符串不为null且不是纯空白字符返回true
     */
    public static boolean hasText(String str) {
        if (isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 去除字符串两端的空白字符
     *
     * @param str 要处理的字符串
     * @return 去除空白后的字符串，如果输入为null返回空字符串
     */
    public static String trimWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.trim();
    }

    /**
     * 比较两个字符串是否相等（处理null情况）
     *
     * @param str1 第一个字符串
     * @param str2 第二个字符串
     * @return 如果两个字符串都为null或内容相等返回true
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * 忽略大小写比较字符串
     *
     * @param str1 第一个字符串
     * @param str2 第二个字符串
     * @return 如果两个字符串忽略大小写后内容相等返回true
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equalsIgnoreCase(str2);
    }

    /**
     * 将字符串集合连接为单个字符串
     *
     * @param collection 字符串集合
     * @param separator 分隔符
     * @return 连接后的字符串
     */
    public static String join(Collection<String> collection, String separator) {
        if (collection == null || collection.isEmpty()) {
            return EMPTY_STRING;
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (String element : collection) {
            if (!first) {
                sb.append(separator);
            }
            sb.append(element);
            first = false;
        }

        return sb.toString();
    }

    /**
     * 将字符串数组连接为单个字符串
     *
     * @param array 字符串数组
     * @param separator 分隔符
     * @return 连接后的字符串
     */
    public static String join(String[] array, String separator) {
        if (array == null || array.length == 0) {
            return EMPTY_STRING;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(array[i]);
        }

        return sb.toString();
    }

    /**
     * 检查字符串是否以指定前缀开头（忽略大小写）
     *
     * @param str 要检查的字符串
     * @param prefix 前缀
     * @return 如果字符串以指定前缀开头返回true
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str == null || prefix == null) {
            return false;
        }
        if (prefix.length() > str.length()) {
            return false;
        }
        return str.substring(0, prefix.length()).equalsIgnoreCase(prefix);
    }

    /**
     * 检查字符串是否以指定后缀结尾（忽略大小写）
     *
     * @param str 要检查的字符串
     * @param suffix 后缀
     * @return 如果字符串以指定后缀结尾返回true
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        if (str == null || suffix == null) {
            return false;
        }
        if (suffix.length() > str.length()) {
            return false;
        }
        return str.substring(str.length() - suffix.length()).equalsIgnoreCase(suffix);
    }
}
