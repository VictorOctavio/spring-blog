package com.practice.demo.services;

import com.practice.demo.dto.PublicationDTO;
import com.practice.demo.dto.PublicationResponse;
import com.practice.demo.entities.Publication;
import com.practice.demo.exceptions.ResourceNotFoundException;
import com.practice.demo.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public PublicationResponse getPublications(int numbPage, int sizePage, String sortBy) {

        // Pagination
        Pageable pageable = PageRequest.of(numbPage, sizePage, Sort.by(sortBy));
        Page<Publication> publications = publicationRepository.findAll(pageable);

        List<Publication> listPublications = publications.getContent();
        List<PublicationDTO> content = listPublications.stream().map(publication -> convertToDTO(publication)).collect(Collectors.toList());

        PublicationResponse publicationResponse = new PublicationResponse();
        publicationResponse.setContent(content);
        publicationResponse.setNumbPage(publications.getNumber());
        publicationResponse.setSizePage(publications.getSize());
        publicationResponse.setTotalElements(publications.getTotalElements());
        publicationResponse.setTotalPages(publications.getTotalPages());
        publicationResponse.setLast(publications.isLast());

        return publicationResponse;
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
