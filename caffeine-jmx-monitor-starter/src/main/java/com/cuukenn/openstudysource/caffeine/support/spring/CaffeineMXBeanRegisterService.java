package com.cuukenn.openstudysource.caffeine.support.spring;

import com.cuukenn.openstudysource.caffeine.management.CaffeineMXbeanRegister;
import com.cuukenn.openstudysource.caffeine.mxbean.CaffeineSamplerMXBeanBuilder;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import javax.management.JMException;

/**
 * @author changgg
 */
@Service
public class CaffeineMXBeanRegisterService implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(CaffeineMXBeanRegisterService.class);
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private CaffeineSamplerMXBeanBuilder mxBeanBuilder;

    @Override
    public void afterPropertiesSet() throws Exception {
        cacheManager.getCacheNames().forEach(cacheName -> {
            try {
//                Caffeine.newBuilder().recordStats().build().stats()
                Cache cache = cacheManager.getCache(cacheName);
                if (cache instanceof CaffeineCache) {
                    CaffeineMXbeanRegister.register(mxBeanBuilder.build(cacheManager, cacheName));
                }
            } catch (JMException e) {
                logger.error("register caffeine sampler failed", e);
            }
        });
    }
}
