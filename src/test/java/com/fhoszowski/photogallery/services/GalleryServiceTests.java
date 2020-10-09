package com.fhoszowski.photogallery.services;

import com.fhoszowski.photogallery.models.Gallery;
import com.fhoszowski.photogallery.models.User;
import com.fhoszowski.photogallery.repositories.GalleryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GalleryServiceTests {
    @InjectMocks
    GalleryService galleryService;
    @Mock
    GalleryRepository galleryRepository;
    @Mock
    UserService userService;
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

    @Test
    void shouldCreateNewGallery() {
        //given
        User user = new User();
        user.setLogin("user");
        Gallery galleryOne = Gallery.builder().galleryname("galleryOne").user(user).build();
        //when
        when(galleryRepository.save(any(Gallery.class))).thenReturn(galleryOne);
        when(userService.getUser("user")).thenReturn(user);
        when(galleryService.getGallery("galleryOne")).thenReturn(galleryOne);
        //then
        galleryService.createNewGallery("galleryOne","user");
        verify(galleryRepository,times(1)).save(galleryOne);
    }

}
