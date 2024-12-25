package com.base.components.log.trace.filter;


import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static com.base.components.log.trace.constant.TraceConstant.TRACE_ID;

public class TraceFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String traceId = request.getHeader("traceId");
            if (StringUtils.isEmpty(traceId)) {
                MDC.put(TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
            } else {
                MDC.put(TRACE_ID, traceId);
            }
            response.addHeader(TRACE_ID, MDC.get(TRACE_ID));
            chain.doFilter(request, response);
        } catch (Throwable var8) {
            throw var8;
        } finally {
            MDC.clear();
        }
    }
}
