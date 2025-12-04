package br.upe.finance.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.upe.finance.dtos.ReadResourceManagementDto;
import br.upe.finance.dtos.ResourceRequestDto;
import br.upe.finance.dtos.mappers.ReadResourceManagementDtoMapper;
import br.upe.finance.dtos.mappers.ResourceRequestDtoMapper;
import br.upe.finance.models.ResourceManagement;
import br.upe.finance.repositories.ResourceManagementRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResourceManagementService {

    /// Fields ///

    private final ResourceManagementRepository rmRepository;

    private final ReadResourceManagementDtoMapper readResourceManagementDtoMapper;

    private final ResourceRequestDtoMapper resourceRequestDtoMapper;

    /// Public Methods ///

    public Optional<ReadResourceManagementDto> findById(UUID id) {
        return rmRepository
            .findById(id)
            .map(readResourceManagementDtoMapper::fromModel);
    }

    public Page<ReadResourceManagementDto> findAll(Pageable pageable) {
        return rmRepository
            .findAll(pageable)
            .map(readResourceManagementDtoMapper::fromModel);
    }

    public ResourceManagement requestResource(
        ResourceRequestDto resourceRequestDto) {
        ResourceManagement resourceManagement = resourceRequestDtoMapper
            .toModel(resourceRequestDto);
        return rmRepository.save(resourceManagement);
    }

}
