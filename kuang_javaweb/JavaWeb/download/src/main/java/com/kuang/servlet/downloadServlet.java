package com.kuang.servlet;

import com.kuang.utils.DownLoadUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author Chenix
 * @create 2023-10-19 14:39
 */
public class downloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setCharacterEncoding("UTF-8");
        resp.addHeader("Content-Type", "text/html;charset=utf-8");
        // 1.要获取下载文件的路径
        String path = "D:\\File\\图片\\兔子.jpg";
        // 2.下载的文件名是什么？
        String fileName = path.substring(path.lastIndexOf("\\") + 1);


        /*
         转换中文名
         String agent = req.getHeader("user-agent");
         fileName = DownLoadUtils.getFileName(agent, fileName);
         */

        // 3.设置浏览器使其支持下载的内容
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        // 4.获取下载文件的输入流
        FileInputStream in = new FileInputStream(path);
        // 5.创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
        // 6.获取 OutputStream 对象
        ServletOutputStream out = resp.getOutputStream();
        // 7.将 FileOutputStream 写入到 buffer缓冲区
        // 8.使用 OutputStream 将缓冲区中的数据输出到客户端
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }

        // 关闭输入输出流
        in.close();
        out.close();
    }
}
