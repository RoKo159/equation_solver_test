package pl.kurs.service;


import org.springframework.stereotype.Component;

@Component
public class Division implements IOperation {

    @Override
    public int apply(int a, int b) {
        if (b == 0 || a == 0)
            throw new UnsupportedOperationException("Cannot divide by zero");
        return a / b;
    }
}
