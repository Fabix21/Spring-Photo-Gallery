package com.fhoszowski.photogallery.repositories;

import com.fhoszowski.photogallery.models.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, String> {

    Gallery findByGalleryname( String galleryname );
}
