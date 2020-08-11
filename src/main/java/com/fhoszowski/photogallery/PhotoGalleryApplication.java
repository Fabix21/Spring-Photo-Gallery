package com.fhoszowski.photogallery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class PhotoGalleryApplication implements WebMvcConfigurer {
    public static void main( String[] args ) {
        SpringApplication.run(PhotoGalleryApplication.class,args);

    }

    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {

        registry.addResourceHandler("gallery/media/**").addResourceLocations("file:media/");
    }

}
