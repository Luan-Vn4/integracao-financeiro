package br.upe.finance.repositories.common;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReadableRepository<T, ID> {

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Page<T> findAll(Pageable pageable);

    Page<T> findAllByIdIn(Collection<ID> uuids, Pageable pageable);

    long count();

}
