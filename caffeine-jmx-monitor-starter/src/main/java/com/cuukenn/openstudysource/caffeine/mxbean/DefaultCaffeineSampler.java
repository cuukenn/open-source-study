package com.cuukenn.openstudysource.caffeine.mxbean;

import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;

/**
 * 默认的caffeine监控信息mbean实现
 *
 * @author changgg
 */
public class DefaultCaffeineSampler implements CaffeineSamplerMXBean {
    private final String cacheName;
    private final CacheManager cacheManager;

    public DefaultCaffeineSampler(String cacheName, CacheManager cacheManager) {
        this.cacheName = cacheName;
        this.cacheManager = cacheManager;
    }

    /**
     * 获取caffeine cache实例
     *
     * @return caffeine cache状态管理器,非空
     */
    private CacheStats getCacheStats() {
        org.springframework.cache.Cache wrappedCache = this.cacheManager.getCache(cacheName);
        if (wrappedCache instanceof CaffeineCache) {
            return ((CaffeineCache) wrappedCache).getNativeCache().stats();
        }
        return CacheStats.empty();
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getCacheName() {
        return cacheName;
    }

    @Override
    public long getRequestCount() {
        return this.getCacheStats().requestCount();
    }

    @Override
    public long getHitCount() {
        return this.getCacheStats().hitCount();
    }

    @Override
    public long getMissCount() {
        return this.getCacheStats().missCount();
    }

    @Override
    public long getLoadSuccessCount() {
        return this.getCacheStats().loadSuccessCount();
    }

    @Override
    public long getLoadFailureCount() {
        return this.getCacheStats().loadFailureCount();
    }

    @Override
    public double getLoadFailureRate() {
        return this.getCacheStats().loadFailureRate();
    }

    @Override
    public long getTotalLoadTime() {
        return this.getCacheStats().totalLoadTime();
    }

    @Override
    public long getEvictionCount() {
        return this.getCacheStats().evictionCount();
    }

    @Override
    public long getEvictionWeight() {
        return this.getCacheStats().evictionWeight();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("清除操作暂未实现");
    }
}
