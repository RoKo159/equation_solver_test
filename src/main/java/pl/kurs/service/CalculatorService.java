package pl.kurs.service;

import org.springframework.stereotype.Service;
import pl.kurs.dao.EquationSolverDao;
import pl.kurs.model.CalculationResult;

import java.util.Stack;


@Service
public class CalculatorService implements ICalculatorService {


    private EquationSolverDao equationSolverDao;
    private Addition addition;
    private Subtraction subtraction;
    private Multiplication multiplication;
    private Division division;


    public CalculatorService(EquationSolverDao equationSolverDao, Addition addition, Subtraction subtraction, Multiplication multiplication, Division division) {
        this.equationSolverDao = equationSolverDao;
        this.addition = addition;
        this.subtraction = subtraction;
        this.multiplication = multiplication;
        this.division = division;
    }


    @Override
    public int evaluate(String input) {
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();


        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                int num = 0;
                while (i < input.length() && Character.isDigit(input.charAt(i))) {
                    num = num * 10 + (input.charAt(i) - '0');
                    i++;

                }
                i--;
                numbers.push(num);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.empty() && hasPrecedence(c, operators.peek())) {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }

                operators.push(c);
            }
        }

        while (!operators.empty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        int mathResult = numbers.pop();
        equationSolverDao.save(new CalculationResult(input, mathResult));
        System.out.println(mathResult);
        return mathResult;
    }


    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    private int applyOperation(char op, int a, int b) {
        switch (op) {
            case '+':
                return addition.apply(a, b);
            case '-':
                return subtraction.apply(a, b);
            case '*':
                return multiplication.apply(a, b);
            case '/':
                return division.apply(a, b);
        }
        return 0;
    }
}
