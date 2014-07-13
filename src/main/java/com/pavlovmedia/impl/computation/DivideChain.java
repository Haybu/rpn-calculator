package com.pavlovmedia.impl.computation;

import com.pavlovmedia.api.parser.Operation;
import com.pavlovmedia.api.parser.Token;
import com.pavlovmedia.api.parser.TokenType;
import com.pavlovmedia.api.computation.Chain;
import com.pavlovmedia.impl.parser.MathToken;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by hmohamed on 7/12/14.
 */
@Component("division")
public class DivideChain implements Chain {

    Chain nextChain;

    public DivideChain() {}

    @Override
    public void setNextChain(Chain chain) {
        nextChain = chain;
    }

    public boolean isValidOperation(String operator) {
        return ( operator.equalsIgnoreCase("/") || operator.equalsIgnoreCase("div"));
    }

    @Override
    public Token compute(Operation operation) {
        Assert.notNull(operation, "operation must not be null");
        String operator = ((String)operation.getOperator().getTokenContent()).toLowerCase();
        if (isValidOperation(operator)) {
            double firstOperand = ((Double)operation.getFirstOperand().getTokenContent()).doubleValue();
            double secondOperand = ((Double)operation.getSecondOperand().getTokenContent()).doubleValue();
            double result = firstOperand / secondOperand;
            return new MathToken(new Double(result), TokenType.OPERAND);
        } else {
            return nextChain.compute(operation);
        }
    }

    @Override
    public int getNumberOfOperands(Token operator) {
        if (isValidOperation((String)operator.getTokenContent())) {
            return 2;
        } else {
            return nextChain.getNumberOfOperands(operator);
        }
    }
}
