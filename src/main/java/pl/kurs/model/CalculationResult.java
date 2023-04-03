package pl.kurs.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "calculation_results")
public class CalculationResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equation_solver")
    private Long id;

    @Column(name = "input")
    private String input;

    @Column(name = "result")
    private int result;

    public CalculationResult() {
    }


    public CalculationResult(String input, int result) {
        this.input = input;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public String getInput() {
        return input;
    }

    public int getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculationResult that = (CalculationResult) o;
        return result == that.result && Objects.equals(id, that.id) && Objects.equals(input, that.input);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, input, result);
    }

    @Override
    public String toString() {
        return "CalculationResult{" +
                "id=" + id +
                ", input='" + input + '\'' +
                ", result=" + result +
                '}';
    }
}
