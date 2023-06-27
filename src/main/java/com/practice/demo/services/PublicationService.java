package com.practice.demo.services;

import com.practice.demo.dto.PublicationDTO;
import com.practice.demo.dto.PublicationResponse;

public interface PublicationService {
    public PublicationDTO savePublication(PublicationDTO publicationDTO);
    public PublicationResponse getPublications(int numbPage, int sizePage, String sortBy);
    public PublicationDTO getPublicationById(Long id);
    public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id);
    public Boolean removePublication(Long id);
}
