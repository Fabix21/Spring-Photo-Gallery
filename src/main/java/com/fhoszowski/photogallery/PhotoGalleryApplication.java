package com.fhoszowski.photogallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@SpringBootApplication
public class PhotoGalleryApplication implements WebMvcConfigurer {
    public static void main( String[] args ) throws IOException {
        SpringApplication.run(PhotoGalleryApplication.class,args);

    }

    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {

        // Register resource handler for images
        // registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
        registry.addResourceHandler("/media/**").addResourceLocations("file:media/");

    }

}
