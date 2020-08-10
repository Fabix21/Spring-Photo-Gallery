package com.fhoszowski.photogallery.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@Entity
@AllArgsConstructor
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String galleryname;

    @OneToMany(mappedBy = "gallery")
    private List<Image> images;

    @ManyToOne
    private User user;

    public Gallery() {
    }
}
