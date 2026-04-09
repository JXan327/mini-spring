package com.minispring.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合工具类
 * 提供常用的集合操作方法
 */
public class CollectionUtils {

    /**
     * 判断集合是否为空
     *
     * @param collection 要检查的集合
     * @return 如果集合为null或没有元素返回true
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合是否不为空
     *
     * @param collection 要检查的集合
     * @return 如果集合不为null且有元素返回true
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断Map是否为空
     *
     * @param map 要检查的Map
     * @return 如果Map为null或没有键值对返回true
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断Map是否不为空
     *
     * @param map 要检查的Map
     * @return 如果Map不为null且有键值对返回true
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 获取集合的大小，如果集合为null返回0
     *
     * @param collection 集合
     * @return 集合大小，null返回0
     */
    public static int size(Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    /**
     * 获取Map的大小，如果Map为null返回0
     *
     * @param map Map
     * @return Map大小，null返回0
     */
    public static int size(Map<?, ?> map) {
        return map == null ? 0 : map.size();
    }

    /**
     * 创建新的ArrayList实例
     *
     * @param <T> 元素类型
     * @return 新的ArrayList
     */
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * 创建指定初始容量的ArrayList
     *
     * @param initialCapacity 初始容量
     * @param <T> 元素类型
     * @return 新的ArrayList
     */
    public static <T> ArrayList<T> newArrayList(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
    }

    /**
     * 创建新的HashMap实例
     *
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 新的HashMap
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    /**
     * 创建指定初始容量的HashMap
     *
     * @param initialCapacity 初始容量
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 新的HashMap
     */
    public static <K, V> HashMap<K, V> newHashMap(int initialCapacity) {
        return new HashMap<>(initialCapacity);
    }

    /**
     * 将集合转换为数组
     *
     * @param collection 集合
     * @param <T> 元素类型
     * @return 数组
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Collection<T> collection) {
        if (isEmpty(collection)) {
            return null;
        }
        return collection.toArray((T[]) new Object[0]);
    }

    /**
     * 使用指定函数转换集合中的每个元素
     *
     * @param collection 原始集合
     * @param mapper 转换函数
     * @param <T> 原始类型
     * @param <R> 目标类型
     * @return 转换后的集合
     */
    public static <T, R> ArrayList<R> transform(Collection<T> collection, Function<T, R> mapper) {
        if (isEmpty(collection)) {
            return newArrayList();
        }
        return collection.stream()
                .map(mapper)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
