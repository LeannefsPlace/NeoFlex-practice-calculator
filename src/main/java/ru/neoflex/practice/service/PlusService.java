package ru.neoflex.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.practice.model.Expression;
import ru.neoflex.practice.repository.CalculationMemoryRepository;

import java.util.List;

@Service
public class PlusService {
    private final CalculationMemoryRepository calculationMemoryRepository;
    @Autowired
    public PlusService(CalculationMemoryRepository calculationMemoryRepository) {
        this.calculationMemoryRepository = calculationMemoryRepository;
    }

    public List<Expression> getAll(){
        return calculationMemoryRepository.getAllByExpressionContains("+");
    }

    public boolean insert(Expression expression){
        try {
            calculationMemoryRepository.save(expression);
            return true;
        }catch(Exception exception)
        {
            return false;
        }
    }
}
