package com.cuukenn.openstudysource.caffeine.mxbean;

import org.springframework.cache.CacheManager;

/**
 * @author changgg
 */
public interface CaffeineSamplerMXBeanBuilder {
    /**
     * 创建
     *
     * @param cacheManager cache管理器
     * @param cacheName    cache名称
     * @return CaffeineSamplerMXBean
     */
    CaffeineSamplerMXBean build(CacheManager cacheManager, String cacheName);
}
