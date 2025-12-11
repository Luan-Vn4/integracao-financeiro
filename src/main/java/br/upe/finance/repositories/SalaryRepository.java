package br.upe.finance.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.upe.finance.models.Salary;
import br.upe.finance.repositories.common.CrudRepository;

@Repository
public interface SalaryRepository
        extends CrudRepository<Salary, UUID>,
                JpaRepository<Salary, UUID>,
                QuerydslPredicateExecutor<Salary> {}
