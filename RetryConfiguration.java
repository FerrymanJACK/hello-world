package com.beta.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

/**
 * @ClassName RetryConfiguration
 * @Desc TODO
 * @Author wangrongqing
 * @Date 29/4/2022 下午2:56
 * @Version 1.0.0
 **/
public class RetryConfiguration {

    private static Logger log = LoggerFactory.getLogger(RetryConfiguration.class);

    /**
     * @description  初始重试间隔为 1000 毫秒，增加倍数为 1.2 倍，最大重试间隔为 5000 毫秒，最大重试次数为 10 次
     *               关闭configserver的条件下启动 web 项目，然后就会看到重试十次之后，项目启动失败
     * @return org.springframework.retry.interceptor.RetryOperationsInterceptor
     * @author wangrongqing
     * @date 29/4/2022 下午2:58
     */
    @Bean
    @ConditionalOnMissingBean(name = "configServerRetryInterceptor")
    public RetryOperationsInterceptor configServerRetryInterceptor() {
        log.info(String.format("configServerRetryInterceptor: Changing backOffOptions " + "to initial: %s, multiplier: %s, maxInterval: %s", 1000, 1.2, 5000));
        return RetryInterceptorBuilder
                .stateless()
                .backOffOptions(1000, 1.2, 5000)
                .maxAttempts(10)
                .build();
    }

}
