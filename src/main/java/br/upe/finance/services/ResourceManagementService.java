package br.upe.finance.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.upe.finance.models.ResourceManagement;
import br.upe.finance.repositories.ResourceManagementRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@AllArgsConstructor
public class ResourceManagementService {

    private final ResourceManagementRepository rmRepository;

    public Mono<ResourceManagement> findById(UUID id) {
        return Mono
            .fromCallable(() -> rmRepository.findById(id))
            .subscribeOn(Schedulers.boundedElastic())
            .flatMap(Mono::justOrEmpty);
    }

    public Mono<ResourceManagement> save(
        ResourceManagement resourceManagement) {
        return Mono
            .fromCallable(() -> rmRepository.save(resourceManagement))
            .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> deleteById(UUID id) {
        return Mono
            .fromRunnable(() -> rmRepository.deleteById(id))
            .subscribeOn(Schedulers.boundedElastic())
            .then();
    }

    public Mono<Boolean> existsById(UUID id) {
        return Mono
            .fromCallable(() -> rmRepository.existsById(id))
            .subscribeOn(Schedulers.boundedElastic());
    }

}
