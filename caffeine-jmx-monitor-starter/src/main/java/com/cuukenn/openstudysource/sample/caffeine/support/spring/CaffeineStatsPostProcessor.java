package com.cuukenn.openstudysource.sample.caffeine.support.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.lang.Nullable;

/**
 * 增强配置，开启caffeine的监控
 *
 * @author changgg
 */
public class CaffeineStatsPostProcessor implements BeanPostProcessor {
    private static final String STATS = "recordStats";

    @Override
    public Object postProcessAfterInitialization(@Nullable Object bean, @Nullable String beanName) throws BeansException {
        if (bean instanceof CacheProperties) {
            String spec = ((CacheProperties) bean).getCaffeine().getSpec();
            if (spec != null && !spec.contains(STATS)) {
                ((CacheProperties) bean).getCaffeine().setSpec(spec + "," + STATS);
            }
        }
        return bean;
    }
}
