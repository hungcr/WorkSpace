package com.springapp.mvc.config;

import com.springapp.mvc.Application;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by LANGKHACH on 20/05/2014.
 */
@Configuration
@ComponentScan(basePackageClasses = Application.class)
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public ViewResolver resolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
