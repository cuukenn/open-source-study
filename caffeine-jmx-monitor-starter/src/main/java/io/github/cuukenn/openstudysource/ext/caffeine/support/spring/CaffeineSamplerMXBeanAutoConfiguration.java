package io.github.cuukenn.openstudysource.ext.caffeine.support.spring;

import io.github.cuukenn.openstudysource.ext.caffeine.mxbean.CaffeineSamplerBuilder;
import io.github.cuukenn.openstudysource.ext.caffeine.mxbean.CaffeineSamplerMXBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "AlibabaClassNamingShouldBeCamel"})
@Configuration
@ConditionalOnClass(CaffeineCache.class)
@ConditionalOnProperty(prefix = "spring.cache.caffeine.mbean", name = "enable", havingValue = "true")
public class CaffeineSamplerMXBeanAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(CaffeineSamplerMXBeanAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean(CaffeineSamplerMXBeanBuilder.class)
    public CaffeineSamplerMXBeanBuilder caffeineSamplerMXBeanBuilder() {
        logger.info("register default mxBeanBuilder");
        return new CaffeineSamplerBuilder();
    }

    @Bean
    public CaffeineMXBeanRegisterService caffeineMXBeanRegisterService() {
        logger.info("register caffeineMXBeanRegisterService");
        return new CaffeineMXBeanRegisterService();
    }

    @Bean
    public BeanPostProcessor caffeineStatProcessor() {
        logger.info("register caffeineStatProcessor");
        return new CaffeineStatsPostProcessor();
    }
}
