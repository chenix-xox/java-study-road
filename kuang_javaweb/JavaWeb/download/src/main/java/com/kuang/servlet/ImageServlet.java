package com.kuang.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author Chenix
 * @create 2023-10-19 15:05
 */
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 如何让浏览器5s刷新一次
        resp.setHeader("refresh", "0.3");

        // 在内存中创建一张图片（宽80，高20，rgb）
        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);

        // 创建笔在该图片上
        Graphics2D g = (Graphics2D) image.getGraphics();
        // 设置图片背景颜色
        //笔转换为白色
        g.setColor(Color.white);
        //设置动笔的起始坐标以及宽高
        g.fillRect(0, 0, 80, 20);
        // 给图片写数据
        g.setColor(Color.blue);
        g.setFont(new Font(null, Font.BOLD, 20));
        g.drawString(randomNum(), 0, 20);

        // 告诉浏览器，这个请求用图片打开
        resp.setContentType("image/jpeg");
        // 网站存在缓存，不让浏览器缓存
        resp.setDateHeader("expires", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");

        // 图片写给image
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    private String randomNum() {
        Random random = new Random();
        String nums = random.nextInt(9999999) + "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7 - nums.length(); i++) {
            sb.append("0");
        }
        return sb.toString() + nums;
    }
}
