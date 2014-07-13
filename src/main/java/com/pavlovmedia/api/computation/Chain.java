package com.pavlovmedia.api.computation;

import com.pavlovmedia.api.parser.Operation;
import com.pavlovmedia.api.parser.Token;

/**
 * Created by hmohamed on 7/12/14.
 */
public interface Chain {

    public void setNextChain(Chain operation);
    public Token compute(Operation operation);
    public int getNumberOfOperands(Token operator);
    public boolean isValidOperation(String operator);
}
