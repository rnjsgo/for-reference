package com.thecommerce.app.common.config;

import com.thecommerce.app.common.logger.RequestLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final RequestLogger requestLogger;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(requestLogger);
    }
}
