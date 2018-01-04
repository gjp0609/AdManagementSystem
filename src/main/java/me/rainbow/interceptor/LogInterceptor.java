package me.rainbow.interceptor;

import com.alibaba.fastjson.JSON;
import me.rainbow.entity.Log;
import me.rainbow.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guojinpeng
 * @date 18.1.3 12:03
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
    private final LogService logService;

    @Autowired
    public LogInterceptor(LogService logService) {
        this.logService = logService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String remoteAddr = request.getRemoteAddr();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        Enumeration<String> names = request.getParameterNames();
        Map<String, String> map = new HashMap<>();
        while (names.hasMoreElements()) {
            String param = names.nextElement();
            String value = request.getParameter(param);
            map.put(param, value);
        }
        String args = JSON.toJSONString(map);
        Log log = new Log();
        log.setIp(remoteAddr);
        log.setUri(uri);
        log.setArgs(args);
        log.setMethod(method);
        logService.saveLog(log);
        return true;
    }
}
