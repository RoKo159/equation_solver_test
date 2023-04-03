package pl.kurs.service;


import org.springframework.stereotype.Component;

@Component
public class Subtraction implements IOperation {

    @Override
    public int apply(int a, int b) {
        return a - b;
    }

}
