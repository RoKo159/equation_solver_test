package pl.kurs.service;


import org.springframework.stereotype.Service;
import pl.kurs.dao.IEquationSolverDao;
import pl.kurs.model.CalculationResult;

@Service
public class CalculationResultService implements ICalculationResultService {

    private IEquationSolverDao equationSolverDao;

    public CalculationResultService(IEquationSolverDao equationSolverDao) {
        this.equationSolverDao = equationSolverDao;
    }

    @Override
    public void saveCalculationResult(CalculationResult calculationResult) {
        equationSolverDao.save(calculationResult);
    }
}
