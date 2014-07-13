package com.pavlovmedia.api.parser;

/**
 * Created by hmohamed on 7/12/14.
 */
public interface Operation {

    public Token getFirstOperand();
    public Token getSecondOperand();
    public Token getOperator();
}
