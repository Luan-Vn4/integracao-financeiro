package br.upe.finance.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.upe.finance.dtos.ReadResourceManagementDto;
import br.upe.finance.dtos.SaveResourceManagementDto;
import br.upe.finance.dtos.mappers.ReadResourceManagementDtoMapper;
import br.upe.finance.dtos.mappers.SaveResourceManagementDtoMapper;
import br.upe.finance.models.ResourceManagement;
import br.upe.finance.repositories.ResourceManagementRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResourceManagementService {

    private final ResourceManagementRepository rmRepository;

    private final ReadResourceManagementDtoMapper readResourceManagementDtoMapper;

    private final SaveResourceManagementDtoMapper saveResourceManagementDtoMapper;

    public Optional<ReadResourceManagementDto> findById(UUID id) {
        return rmRepository.findById(id)
            .map(readResourceManagementDtoMapper::fromModel);
    }

    public ResourceManagement save(
        SaveResourceManagementDto saveResourceManagementDto) {
        ResourceManagement resourceManagement = saveResourceManagementDtoMapper
            .toModel(saveResourceManagementDto);
        return rmRepository.save(resourceManagement);
    }

    public void deleteById(UUID id) {
        rmRepository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return rmRepository.existsById(id);
    }

}
