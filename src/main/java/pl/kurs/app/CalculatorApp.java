package pl.kurs.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.kurs.service.ICalculatorService;

@ComponentScan(basePackages = "pl.kurs")
public class CalculatorApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(CalculatorApp.class);
        ICalculatorService calculatorService = ctx.getBean(ICalculatorService.class);
        calculatorService.evaluate(args[0]);
    }
}
