package com.practice.demo.services;

import com.practice.demo.dto.PublicationDTO;

import java.util.List;

public interface PublicationService {
    public PublicationDTO savePublication(PublicationDTO publicationDTO);
    public List<PublicationDTO> getPublications();
    public PublicationDTO getPublicationById(Long id);
    public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id);
    public Boolean removePublication(Long id);
}
