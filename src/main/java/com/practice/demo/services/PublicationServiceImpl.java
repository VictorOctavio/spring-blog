package com.practice.demo.services;

import com.practice.demo.dto.PublicationDTO;
import com.practice.demo.entities.Publication;
import com.practice.demo.exceptions.ResourceNotFoundException;
import com.practice.demo.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public PublicationDTO savePublication(PublicationDTO publicationDTO) {

        // Convert DTO to Entity
        Publication publication = convertToEntity(publicationDTO);

        Publication newPublication = publicationRepository.save(publication);

        // Convert entity to DTO
        PublicationDTO publicationRes = convertToDTO(newPublication);

        return publicationRes;
    }

    @Override
    public List<PublicationDTO> getPublications() {
        List<Publication> publications = publicationRepository.findAll();
        return publications.stream().map(publication -> convertToDTO(publication)).collect(Collectors.toList());
    }

    @Override
    public PublicationDTO getPublicationById(Long id) {
        Publication publication = publicationRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("publication", "id", id)
                );
        return convertToDTO(publication);
    }

    @Override
    public PublicationDTO updatePublication(PublicationDTO publicationDTO, Long id) {

        Publication publication = publicationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("publication", "id", id)
        );

        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());

        Publication publicationUpdated = publicationRepository.save(publication);
        return convertToDTO(publicationUpdated);
    }

    @Override
    public Boolean removePublication(Long id) {
        Publication publication = publicationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("publication", "id", id)
        );
        publicationRepository.deleteById(id);
        return true;
    }

    private PublicationDTO convertToDTO(Publication publication){
        PublicationDTO publicationDTO = new PublicationDTO();
        publicationDTO.setTitle(publication.getTitle());
        publicationDTO.setContent(publication.getContent());
        publicationDTO.setDescription(publication.getDescription());
        publicationDTO.setId(publication.getId());
        return  publicationDTO;
    }

    private  Publication convertToEntity(PublicationDTO publicationDTO){
        Publication publication = new Publication();
        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());
        return publication;
    }

}
