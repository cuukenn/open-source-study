package com.cuukenn.openstudysource.sample.caffeine.mxbean;

/**
 * caffeine监控mxbean
 *
 * @author changgg
 */
public interface CaffeineSamplerMXBean {
    /**
     * 实现名称
     *
     * @return String
     */
    default String getName() {
        return "caffeine-sample";
    }

    /**
     * 缓存名称
     *
     * @return String
     */
    String getCacheName();

    /**
     * 请求次数
     *
     * @return long
     */
    long getRequestCount();

    /**
     * 命中次数
     *
     * @return long
     */
    long getHitCount();

    /**
     * 未命中次数
     *
     * @return long
     */
    long getMissCount();

    /**
     * 加载成功次数
     *
     * @return long
     */
    long getLoadSuccessCount();

    /**
     * 加载失败次数
     *
     * @return long
     */
    long getLoadFailureCount();

    /**
     * 加载失败占比
     *
     * @return double
     */
    double getLoadFailureRate();

    /**
     * 加载总耗时
     *
     * @return long
     */
    long getTotalLoadTime();

    /**
     * 回收总次数
     *
     * @return long
     */
    long getEvictionCount();

    /**
     * 回收总权重
     *
     * @return long
     */
    long getEvictionWeight();

    /**
     * 重置监控状态
     */
    void clear();
}
