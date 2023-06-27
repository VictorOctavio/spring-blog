package com.practice.demo.controllers;

import com.practice.demo.dto.PublicationDTO;
import com.practice.demo.dto.PublicationResponse;
import com.practice.demo.services.PublicationService;
import com.practice.demo.utilities.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/publications")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @GetMapping
    public PublicationResponse getPublications(
            @RequestParam(value = "numbPage", defaultValue = AppConstant.NUMB_PAGE_DEFAULT, required = false) int numbPage,
            @RequestParam(value = "sizePage", defaultValue = AppConstant.SIZE_PAGE_DEFAULT, required = false) int sizePage,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy
    ){
        return  publicationService.getPublications(numbPage, sizePage, sortBy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> getPublication(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(
                publicationService.getPublicationById(id),
                HttpStatus.FOUND
        );
    }

    @PostMapping
    public ResponseEntity<PublicationDTO> savePublication(@RequestBody PublicationDTO publicationDTO){
        return new ResponseEntity<>(
                publicationService.savePublication(publicationDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationDTO> updatePublication(@PathVariable(name = "id") Long id, @RequestBody PublicationDTO publicationDTO){
        return new ResponseEntity<>(
                publicationService.updatePublication(publicationDTO, id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public Boolean removePublication(@PathVariable(name = "id") Long id){
        return publicationService.removePublication(id);
    }
}
