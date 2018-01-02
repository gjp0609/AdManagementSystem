package me.rainbow.controller;

import me.rainbow.entity.Log;
import me.rainbow.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author guojinpeng
 * @date 17.12.29 15:34
 */
@RequestMapping("/test")
@Controller("testController")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    private final LogService service;

    @Autowired
    public TestController(LogService service) {
        log.info("LogService Autowired");
        this.service = service;
    }

    @RequestMapping(params = "index")
    public String testThymeleaf(HttpServletRequest request) {
        log.info("index page");
        request.setAttribute("msg", "hello");
        return "index";
    }

    @RequestMapping(params = "ths")
    public String ths(HttpServletRequest request) {
        request.setAttribute("text1", "hello world");
        request.setAttribute("text2", "张三");
        request.setAttribute("int", 3);
        request.setAttribute("double", 3.43D);
        request.setAttribute("boolean", true);
        request.setAttribute("src", "http://59.110.230.101/index/files/1.png");
        request.setAttribute("url", "http://59.110.230.101/index/index.html");
        request.setAttribute("email", "gjp0609@163.com");
        return "test/ths";
    }

    @RequestMapping(params = "foreach")
    public String foreach(HttpServletRequest request) {
        log.info("foreach page");
        List<Log> logs = service.getAllLogs();
        request.setAttribute("logs", logs);
        return "test/foreach";
    }

}