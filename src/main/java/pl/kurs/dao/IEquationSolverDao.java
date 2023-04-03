package pl.kurs.dao;

import pl.kurs.model.CalculationResult;

public interface IEquationSolverDao {

    void save(CalculationResult calculationResult);

    CalculationResult get(Long id);
}
