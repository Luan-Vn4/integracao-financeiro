package br.upe.finance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.upe.finance.models.Budget;
import br.upe.finance.repositories.common.CrudRepository;

@Repository
public interface BudgetRepository
        extends CrudRepository<Budget, Short>,
        JpaRepository<Budget, Short>,
        QuerydslPredicateExecutor<Budget> {
}