package pl.kurs.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.kurs.model.CalculationResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;


@Repository
public class EquationSolverDao implements IEquationSolverDao {

    @Autowired
    private EntityManagerFactory factory;


    @Override
    public void save(CalculationResult calculationResult) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(calculationResult);
        tx.commit();
        entityManager.close();
    }

    @Override
    public CalculationResult get(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        CalculationResult calculationResult = entityManager.find(CalculationResult.class, id);
        entityManager.close();
        return calculationResult;
    }
}
