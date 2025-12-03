package br.upe.finance.repositories.common;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CrudRepository<T, ID>
    extends ReadableRepository<T, ID>, PersistableRepository<T, ID>,
    RemovableRepository<T, ID> {

}
