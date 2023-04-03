package pl.kurs.service;


import org.springframework.stereotype.Component;

@Component
public interface IOperation {
    int apply(int a, int b);
}
