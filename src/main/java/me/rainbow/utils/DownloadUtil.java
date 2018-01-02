package me.rainbow.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author guojinpeng
 * @date 18.1.2 11:18
 */
public class DownloadUtil {

    public static void fileDownload(HttpServletRequest request, HttpServletResponse response, File file, String fileName) throws IOException {
        if (file.exists()) {
            java.io.BufferedInputStream bis = null;
            java.io.BufferedOutputStream bos = null;
            try {
                response.setContentType("application/octet-stream");
                String charset;
                if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) charset = "UTF-8";
                else charset = "ISO8859-1";
                response.setHeader("content-disposition",
                        "attachment;filename=" + new String(fileName.getBytes("gb2312"), charset));
                bis = new BufferedInputStream(new FileInputStream(file));
                bos = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } finally {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            }
        }
    }
}
