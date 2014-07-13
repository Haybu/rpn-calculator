package com.pavlovmedia.impl.parser;

import com.pavlovmedia.api.parser.Token;
import com.pavlovmedia.api.parser.TokenType;

/**
 * Created by hmohamed on 7/12/14.
 */
public class MathToken implements Token {
    Object body;
    TokenType type;

    public MathToken(Object _object, TokenType _type) {
        body = _object;
    }

    @Override
    public Object getTokenContent() {
        return body;
    }

    @Override
    public TokenType getTokenType() {
        return type;
    }
}
