package me.rainbow.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guojinpeng
 * @date 18.1.3 11:04
 */
public class TestInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(TestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;
        String url = request.getRequestURL().toString();
        log.info("info url:{}", url);
        if (url.contains("/test")) {
            String token = request.getParameter("token");
            if (StringUtils.isNoneBlank(token)) {
                log.info("token:{}", token);
                flag = true;
            } else flag = false;
        }
        return flag;
    }
}
