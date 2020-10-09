package com.fhoszowski.photogallery.services;

import com.fhoszowski.photogallery.models.Gallery;
import com.fhoszowski.photogallery.repositories.GalleryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class GalleryServiceTests {
    @InjectMocks
    GalleryService galleryService;
    @Mock
    GalleryRepository galleryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetGallery() {
        //given
        Gallery gallery = Gallery.builder().galleryname("gallery").build();
        //when
        when(galleryRepository.findByGalleryname(anyString())).thenReturn(gallery);
        //then
        assertEquals("gallery",galleryService.getGallery("gallery").getGalleryname());
    }

    @Test
    void shouldGetGalleriesNames() {
        //given
        List<Gallery> galleries = new ArrayList<>();
        Gallery galleryOne = Gallery.builder().galleryname("galleryOne").build();
        Gallery galleryTwo = Gallery.builder().galleryname("galleryTwo").build();

        galleries.add(galleryOne);
        galleries.add(galleryTwo);

        List<String> galleriesNames = new ArrayList<>();
        galleriesNames.add("galleryOne");
        galleriesNames.add("galleryTwo");

        //when
        when(galleryRepository.findAll()).thenReturn(galleries);
        //then
        assertEquals(galleriesNames,galleryService.getGalleriesNames());


    }

}
