package me.rainbow.controller;

import me.rainbow.entity.Log;
import me.rainbow.service.LogService;
import me.rainbow.utils.QrCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

/**
 * @author guojinpeng
 * @date 17.12.29 15:34
 */
@RequestMapping("/test")
@Controller("testController")
public class TestController {
    private static final HashMap<Long, String> THREAD_LOCAL_MAP = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    private final LogService service;

    @Autowired
    public TestController(LogService service) {
        this.service = service;
    }

    @RequestMapping(params = "list")
    @ResponseBody
    public List<Log> queryAll() {
        return service.getAllLogs();
    }

    @RequestMapping(params = "get")
    @ResponseBody
    public Log get(Integer id) {
        return service.getLog(id);
    }

    @RequestMapping(params = "getQrImage")
    @ResponseBody
    public void getQrImage(String context, HttpServletResponse response) {
        File file = new File("C:/Files/Data/123.png");
        boolean encode = QrCodeUtil.encode(context, file);
        if (encode) {
            response.setContentType("image/*");
            FileInputStream fis = null;
            OutputStream os = null;
            try {
                fis = new FileInputStream(file);
                os = response.getOutputStream();
                byte[] buffer = new byte[1024 * 8];
                int count;
                while ((count = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, count);
                    os.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) fis.close();
                    if (os != null) os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(params = "uploadQrCode")
    @ResponseBody
    public void uploadQrCode(MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        log.info("fileName:{}", filename);
        File file = new File(new File("").getAbsolutePath() + "/" + filename);
        log.info("file:{}", file.getAbsolutePath());
        try {
            file.createNewFile();
            multipartFile.transferTo(file);
            String decode = QrCodeUtil.decode(file);
            THREAD_LOCAL_MAP.put(Thread.currentThread().getId(), decode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(params = "getContent")
    @ResponseBody
    public String uploadQrCode() {
        return THREAD_LOCAL_MAP.get(Thread.currentThread().getId());
    }
}