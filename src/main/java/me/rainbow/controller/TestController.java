package me.rainbow.controller;

import me.rainbow.entity.Log;
import me.rainbow.service.LogService;
import me.rainbow.utils.DownloadUtil;
import me.rainbow.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author guojinpeng
 * @date 17.12.29 15:34
 */
@RequestMapping("/test")
@Controller
public class TestController extends BaseController {
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
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("key", "xxx");
        service.testAspectJ(logs.get(0), hashMap);
        request.setAttribute("logs", logs);
        return "test/foreach";
    }

    @RequestMapping(params = "excel")
    public void excel(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Log> allLogs = service.getAllLogs();
            ExcelUtil.getExcel(allLogs, null);
            DownloadUtil.fileDownload(request, response, null, "123.xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}