package com.practice.demo.controllers;

import com.practice.demo.dto.PublicationDTO;
import com.practice.demo.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/publications")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @PostMapping
    public ResponseEntity<PublicationDTO> savePublication(@RequestBody PublicationDTO publicationDTO){
        return new ResponseEntity<>(
                publicationService.savePublication(publicationDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<PublicationDTO>> getPublications(){
        return new ResponseEntity<>(
                publicationService.getPublications(),
                HttpStatus.FOUND
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> getPublication(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(
                publicationService.getPublicationById(id),
                HttpStatus.FOUND
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
