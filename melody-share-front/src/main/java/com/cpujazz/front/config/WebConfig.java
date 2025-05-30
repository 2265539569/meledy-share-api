package com.cpujazz.front.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许的域名，可以使用*表示允许所有
                .allowedOrigins("http://localhost:5173")
                // 允许的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // 允许的请求头
                .allowedHeaders("*")
                // 允许携带cookie
                .allowCredentials(true);
    }
}
