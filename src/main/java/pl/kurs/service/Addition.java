package pl.kurs.service;


import org.springframework.stereotype.Component;

@Component
public class Addition implements IOperation {

    @Override
    public int apply(int a, int b) {
        return a + b;
    }
}
