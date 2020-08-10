package com.fhoszowski.photogallery.repositories;

import com.fhoszowski.photogallery.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

}
