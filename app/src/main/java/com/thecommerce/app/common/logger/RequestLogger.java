package com.thecommerce.app.common.logger;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * HTTP Request의 Method, URI, Query Parameter 로깅
 */
@Slf4j
@Component
public class RequestLogger implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler) {

        // 서로 다른 스레드에 대한 요청을 구분하기 위한 ID를 생성
        MDC.put("requestId", UUID.randomUUID()
                .toString()
                .substring(0, 8));

        log.info("{} {}{}",
                request.getMethod(),
                request.getRequestURI(),
                getQueryParameters(request));

        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler,
            final Exception ex) {
        // 요청 처리가 끝난 후 requestId 클리어
        MDC.remove("requestId");
    }

    // HTTP Request의 Query Parameter 추출
    private String getQueryParameters(final HttpServletRequest request) {
        String queryString = request.getQueryString();

        if (queryString == null) {
            return StringUtils.EMPTY;
        }
        return "?" + queryString;
    }
}
