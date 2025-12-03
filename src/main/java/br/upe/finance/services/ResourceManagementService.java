package br.upe.finance.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.upe.finance.models.ResourceManagement;
import br.upe.finance.repositories.ResourceManagementRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResourceManagementService {

    private final ResourceManagementRepository rmRepository;

    public Optional<ResourceManagement> findById(UUID id) {
        return rmRepository.findById(id);
    }

    public ResourceManagement save(ResourceManagement resourceManagement) {
        return rmRepository.save(resourceManagement);
    }

    public void deleteById(UUID id) {
        rmRepository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return rmRepository.existsById(id);
    }

}
