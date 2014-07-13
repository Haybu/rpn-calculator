package com.pavlovmedia.impl.parser;

import com.pavlovmedia.api.parser.Operation;
import com.pavlovmedia.api.parser.Token;

/**
 * Created by hmohamed on 7/12/14.
 */
public class MathOperation implements Operation {

    private Token firstOperand, secondOperand, operator;

    public MathOperation() {

    }

    public MathOperation (Token _operator, Token _fOperand) {
        this(_operator, _fOperand, null);
    }

    public MathOperation (Token _operator, Token _fOperand, Token _sOperand) {
        firstOperand = _fOperand;
        secondOperand = _sOperand;
        operator = _operator;
    }

    @Override
    public Token getFirstOperand() {
        return firstOperand;
    }

    @Override
    public Token getSecondOperand() {
        return secondOperand;
    }

    @Override
    public Token getOperator() {
        return operator;
    }
}
