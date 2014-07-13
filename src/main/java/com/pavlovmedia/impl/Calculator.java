package com.pavlovmedia.impl;

import com.pavlovmedia.api.computation.Chain;
import com.pavlovmedia.api.parser.Expression;
import com.pavlovmedia.api.parser.Operation;
import com.pavlovmedia.api.parser.Token;
import com.pavlovmedia.impl.parser.MathExpression;
import com.pavlovmedia.impl.parser.MathOperation;
import com.pavlovmedia.impl.parser.MathToken;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by hmohamed on 7/12/14.
 */
@Component("calculator")
public class Calculator implements InitializingBean{

    @Autowired
    Expression expression;

    @Autowired
    @Qualifier("start")
    Chain start;

    @Autowired
    @Qualifier("addition")
    Chain addition;

    @Autowired
    @Qualifier("subtraction")
    Chain subtraction;

    @Autowired
    @Qualifier("multiplication")
    Chain multiplication;

    @Autowired
    @Qualifier("division")
    Chain division;

    @Autowired
    @Qualifier("cosine")
    Chain cosine;

    @Autowired
    @Qualifier("sine")
    Chain sine;

    @Autowired
    @Qualifier("power")
    Chain power;

    public Calculator() {}

    public void init() {
        start.setNextChain(addition);
        addition.setNextChain(subtraction);
        subtraction.setNextChain(multiplication);
        multiplication.setNextChain(division);
        division.setNextChain(power);
        power.setNextChain(cosine);
        cosine.setNextChain(sine);
    }

    public Token compute (Operation operation) {
        return start.compute(operation);
    }

    public Double evaluate (String expValue) {
        Assert.notNull(expValue, "Expression should not be null");

        expression.setExpression(expValue);
        expression.configure();

        if (expression.isValid()) {
            return new Double(process());
        } else {
            System.out.print("\nInvalid expression: " + expValue + "\n");
            return null;
        }
    }

    private double process() {

        Token operator, firstOperand, secondOperand, result;
        Operation operation;
        while (!expression.getOperatorQueue().isEmpty()) {
            operator = firstOperand = secondOperand = result = null;
            operator = (MathToken) expression.getOperatorQueue().poll();
            int numberOfOperands = start.getNumberOfOperands(operator);
            firstOperand = (MathToken) expression.getOperandStack().pop();
            if (numberOfOperands > 1) {
                secondOperand = (MathToken) expression.getOperandStack().pop();
            }

            operation = new MathOperation(operator, firstOperand, secondOperand);
            // compute
            result = start.compute(operation);
            // push it
            expression.getOperandStack().push(result);
        }

        // after the loop is done, there should be no operators left, and only one result in the operand stack
       if (isValidEndOfComputation(expression)) {
           result = (MathToken) expression.getOperandStack().pop();
           Double value = (Double) result.getTokenContent();
           return value.doubleValue();
       } else {
           int operandStackSize = expression.getOperandStack().size();
           int operatorStackSize = expression.getOperatorQueue().size();
           String msg = "Operand Stack Size left = " + operandStackSize +
                   "\nOperator Stack size left = " + operatorStackSize;
           throw new RuntimeException("Error in computation, stacks are not computed correctly\n" + msg);
       }
    }

    private boolean isValidEndOfComputation(Expression expression) {
        return  (expression.getOperandStack().size() == 1 && expression.getOperatorQueue().size() == 0 );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
