package com.pavlovmedia.impl.computation;

import com.pavlovmedia.api.computation.Chain;
import com.pavlovmedia.api.parser.Operation;
import com.pavlovmedia.api.parser.Token;
import org.springframework.stereotype.Component;

/**
 * Created by hmohamed on 7/12/14.
 */
@Component("end")
public class EndChain implements Chain {

    public EndChain() {}

    @Override
    public void setNextChain(Chain chain) {
        throw new UnsupportedOperationException("\nThis is the last step in the chain");
    }

    public boolean isValidOperation(String operator) {
        return false;
    }

    @Override
    public Token compute(Operation operation) {
        System.out.println("\nUnsupported operation.!");
        return null;
    }

    @Override
    public int getNumberOfOperands(Token operator) {
            return 0;
    }
}
