package com.fhoszowski.photogallery.services;

import com.fhoszowski.photogallery.models.Gallery;
import com.fhoszowski.photogallery.models.Image;
import com.fhoszowski.photogallery.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryService {

    @Autowired
    GalleryRepository galleryRepository;

    @Autowired
    UserService userService;

    public List<String> getGalleriesNames() {
        return galleryRepository.findAll()
                                .stream()
                                .map(Gallery::getGalleryname)
                                .collect(Collectors.toList());
    }

    public void createNewGallery( String galleryName,String selectedUser ) {
        Gallery gallery = Gallery
                .builder()
                .user(userService.getUser(selectedUser))
                .galleryname(galleryName).build();

        galleryRepository.save(gallery);
    }

    public Gallery getGallery( String name ) {
        return galleryRepository.findByGalleryname(name);
    }


    public List<String> getImagesFromGallery( String selectedGallery ) {
        return getGallery(selectedGallery)
                .getImages()
                .stream()
                .map(Image::getPath)
                .collect(Collectors.toList());
    }
}
