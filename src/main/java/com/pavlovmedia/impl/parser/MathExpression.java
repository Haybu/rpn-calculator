package com.pavlovmedia.impl.parser;

import com.pavlovmedia.api.parser.Expression;
import com.pavlovmedia.api.parser.Token;
import com.pavlovmedia.api.parser.TokenType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by hmohamed on 7/12/14.
 */
@Component("mathExpression")
public class MathExpression implements Expression {

    private String expression;
    private Stack<Token> operandStack;
    private Queue<Token> operatorQueue;

    private boolean valid;

    public MathExpression() {
        valid = true;
    }

    public MathExpression(String exp) {
        Assert.notNull(expression, "expression must have a value");
        setExpression(exp);
        configure();
    }

    @Override
    public boolean isValid() {
        return valid && (!operandStack.empty() && !operatorQueue.isEmpty()
                && (operandStack.size() == operatorQueue.size() + 1));
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String[] getParts() {
       return expression.split(" ");
    }

    public Stack getOperandStack() {
        return operandStack;
    }

    public void setOperandStack(Stack operandStack) {
        this.operandStack = operandStack;
    }

    public Queue<Token> getOperatorQueue() {
        return operatorQueue;
    }

    public void setOperatorQueue(Queue<Token> _operatorQueue) {
        this.operatorQueue = _operatorQueue;
    }

    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

    public void configure() {
        String[] parts = getParts();
        Token token = null;

        setOperatorQueue(null);
        setOperandStack(null);
        valid = true;

        this.setOperatorQueue(new LinkedList<Token>());
        setOperandStack(new Stack<Token>());

        boolean operandScanned = false;
        boolean operatorScanned = false;

        for(String part: parts) {
            if (isNumeric(part)) {
                if (operatorScanned) {
                    valid = false;
                }
                operandScanned = true;
                token = new MathToken(Double.valueOf(part), TokenType.OPERAND);
                operandStack.push(token);
            } else {
                if (!operandScanned) {
                    valid = false;
                }
                operatorScanned = true;
                token = new MathToken(part, TokenType.OPERATION);
                operatorQueue.add(token);
            }
        }
    }
}
