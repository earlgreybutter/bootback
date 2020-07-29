package com.example.bootback.config;

import com.example.bootback.interceptor.CommentAuthInterceptor;
import com.example.bootback.interceptor.LoginInterceptor;
import com.example.bootback.interceptor.PostAuthInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebserviceConfig implements WebMvcConfigurer {
    
    @Autowired
    LoginInterceptor loginInterceptor;

    @Autowired
    PostAuthInterceptor postAuthInterceptor;

    @Autowired
    CommentAuthInterceptor commentAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
            .excludePathPatterns("/post/page")
            .addPathPatterns("/post/**")
            .addPathPatterns("/comment/**");

        registry.addInterceptor(postAuthInterceptor)
            .excludePathPatterns("/post/page")
            .excludePathPatterns("/post/**/comment/**")
            .addPathPatterns("/post/**");

        registry.addInterceptor(commentAuthInterceptor)
            .addPathPatterns("/post/**/comment/**");
    }
    // 게시글 생성은 모든 유저가 할 수 있어야하므로 예외 처리 
}