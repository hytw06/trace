package com.axe.trace.sys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义资源映射
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 设置虚拟路径，访问绝对路径下资源
        registry.addResourceHandler(FileUploadConfig.UPLOAD_VIRTURAL_PATH + "**")
                .addResourceLocations("file:" + FileUploadConfig.UPLOAD_BASE_PATH);
    }

}

