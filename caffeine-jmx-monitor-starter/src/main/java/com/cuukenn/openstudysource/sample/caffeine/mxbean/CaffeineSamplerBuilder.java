package com.cuukenn.openstudysource.sample.caffeine.mxbean;

import org.springframework.cache.CacheManager;

/**
 * @author changgg
 */
public class CaffeineSamplerBuilder implements CaffeineSamplerMXBeanBuilder {
    @Override
    public CaffeineSamplerMXBean build(CacheManager cacheManager, String cacheName) {
        return new DefaultCaffeineSampler(cacheName, cacheManager);
    }
}