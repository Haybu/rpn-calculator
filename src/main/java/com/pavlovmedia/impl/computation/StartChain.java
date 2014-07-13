package com.pavlovmedia.impl.computation;

import com.pavlovmedia.api.computation.Chain;
import com.pavlovmedia.api.parser.Operation;
import com.pavlovmedia.api.parser.Token;
import com.pavlovmedia.api.parser.TokenType;
import com.pavlovmedia.impl.parser.MathToken;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by hmohamed on 7/12/14.
 */
@Component("start")
public class StartChain implements Chain {

    Chain nextChain;

    public StartChain() {}

    @Override
    public void setNextChain(Chain chain) {
        nextChain = chain;
    }

    public boolean isValidOperation(String operator) {
        return true;
    }

    @Override
    public Token compute(Operation operation) {
            return nextChain.compute(operation);
    }

    @Override
    public int getNumberOfOperands(Token operator) {
            return nextChain.getNumberOfOperands(operator);
    }
}
