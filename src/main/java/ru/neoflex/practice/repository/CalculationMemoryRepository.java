package ru.neoflex.practice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.practice.model.Expression;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalculationMemoryRepository extends CrudRepository<Expression, String> {

    List<Expression> getAllByExpressionContains(String contains);

    Optional<Expression> getByExpression(String expression);

}
