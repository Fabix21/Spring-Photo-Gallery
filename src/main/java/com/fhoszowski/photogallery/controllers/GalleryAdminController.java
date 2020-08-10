package com.fhoszowski.photogallery.controllers;

import com.fhoszowski.photogallery.services.GalleryService;
import com.fhoszowski.photogallery.services.ImageService;
import com.fhoszowski.photogallery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class GalleryAdminController {

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @Autowired
    GalleryService galleryService;

    @GetMapping("/newGallery")
    public String newGallery( Model model ) {
        List<String> usersLogin = userService.getUsersLogin();
        model.addAttribute("usersLogin",usersLogin);
        return "newGallery";
    }

    @PostMapping("/uploadGallery")
    public String newGallery( @RequestParam("selectedUser") String selectedUser,@RequestParam("galleryName") String galleryName,RedirectAttributes redirectAttributes ) {
        galleryService.createNewGallery(galleryName,selectedUser);
        redirectAttributes.addFlashAttribute("message","Pomyślnie stworzono nową galerię!");
        return "redirect:/newGallery";
    }

    @GetMapping("/addPhoto")
    public String addPhoto( Model model ) {
        List<String> galleriesNames = galleryService.getGalleriesNames();
        model.addAttribute("usersLogin",galleriesNames);
        return "addPhoto";
    }

    @PostMapping("/upload")
    public String uploadPhoto( @RequestParam("file") MultipartFile image,@RequestParam("selectedUser") String selectedUser,RedirectAttributes attributes ) throws IOException {
        // check if file is empty
        if (image.isEmpty()) {
            attributes.addFlashAttribute("message","Wybierz zdjecie do uploadu");
            return "redirect:/addPhoto";
        }
        /* check for maximum allowed photo size
        AWS ONLY
        if (image.getSize() > 1000000) {
            attributes.addFlashAttribute("message","Zdjęcie jest za duże, maksymalna dopuszczałna wartość to 1MB !");
            return "redirect:/addPhoto";
        }
        */
        imageService.saveImage(image,selectedUser);

        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        attributes.addFlashAttribute("message","Udało się zaploudować zdjęcie  " + fileName + '!');
        return "redirect:/addPhoto";
    }
}
