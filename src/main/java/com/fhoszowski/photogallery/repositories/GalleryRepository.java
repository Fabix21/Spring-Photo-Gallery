package com.fhoszowski.photogallery.repositories;

import com.fhoszowski.photogallery.models.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GalleryRepository extends JpaRepository<Gallery, String> {

    Gallery findByGalleryname( String galleryname );
}
