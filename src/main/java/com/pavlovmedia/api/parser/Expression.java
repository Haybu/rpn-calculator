package com.pavlovmedia.api.parser;

import java.util.Queue;
import java.util.Stack;

/**
 * Created by hmohamed on 7/12/14.
 */
public interface Expression {

    public void setExpression(String expression);
    public boolean isValid ();
    public String[] getParts();
    public void configure();
    public Stack<Token> getOperandStack();
    public Queue<Token> getOperatorQueue();

}
