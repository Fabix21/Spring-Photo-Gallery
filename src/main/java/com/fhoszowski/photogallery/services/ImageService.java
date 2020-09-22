package com.fhoszowski.photogallery.services;

import com.fhoszowski.photogallery.models.Gallery;
import com.fhoszowski.photogallery.models.Image;
import com.fhoszowski.photogallery.repositories.GalleryRepository;
import com.fhoszowski.photogallery.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    GalleryRepository galleryRepository;

    @Autowired
    UserService userService;


    public List<Image> getImagesByUsername( Principal username ) {

        List<Gallery> galleries = userService.getUser(username.getName()).getGalleries();
        List<Image> imagesFromAllGalleries = new ArrayList<>();
        for (Gallery gallery : galleries) {
            imagesFromAllGalleries.addAll(gallery.getImages());
        }
        return imagesFromAllGalleries;
    }

    public List<String> convertToBase64( List<Image> images ) {
        return images.stream()
                     .map(image -> Base64.getEncoder().encodeToString(image.getImage()))
                     .collect(Collectors.toList());
    }

    public void saveImage( MultipartFile file,String selectedGallery ) throws IOException {
        final String UPLOAD_DIR = "media/";

        Files.createDirectories(Paths.get(UPLOAD_DIR + selectedGallery.concat("/")));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Path path = Paths.get(UPLOAD_DIR + selectedGallery.concat("/") + fileName);
        Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);

        //Store image to DB
        Image image = Image
                .builder()
                //.image(file.getBytes())
                .path("media/" + selectedGallery.concat("/") + fileName)
                .gallery(galleryRepository.findByGalleryname(selectedGallery))
                .build();

        imageRepository.save(image);

    }
}
