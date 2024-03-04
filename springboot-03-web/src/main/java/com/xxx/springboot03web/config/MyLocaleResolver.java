package com.xxx.springboot03web.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * @author Chenix
 * @create 2024-02-20 23:48
 */


public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 获取请求语言参数
        String language = request.getParameter("l");

        // 获取默认的
        Locale locale = Locale.getDefault();

        // 请求连接是否携带国际化参数
        if (!StringUtils.isEmpty(language)){
            String[] split = language.split("_");
            return new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
