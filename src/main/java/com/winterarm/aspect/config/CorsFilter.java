package com.winterarm.aspect.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {


    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);

    private final String allowedOrigins = "*";
    private final String allowedHeaders = "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,accept-api-version,platform-second-type,openId,appName,platformType,sign,appVersionNo,userId,token,mobileBrand,callTime,mobileStandard,channelCode,validTime,data";
    private final String allowedMethods = "GET,POST,HEAD,OPTIONS,PUT";
    private final String exposeHeaders = "";
    private final String maxAge = "3600";


    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
                                                                                            ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        HttpServletRequest reqs = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", allowedOrigins);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", allowedMethods);
        response.setHeader("Access-Control-Max-Age", maxAge);
        response.setHeader("Access-Control-Allow-Headers", allowedHeaders);
        chain.doFilter(reqs, response);

    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}