package br.upe.finance.repositories.common;

public interface CrudRepository<T, ID>
                extends ReadableRepository<T, ID>, PersistableRepository<T, ID>,
                RemovableRepository<T, ID> {

}
