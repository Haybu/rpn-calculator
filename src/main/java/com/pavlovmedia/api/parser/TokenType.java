package com.pavlovmedia.api.parser;

/**
 * Created by hmohamed on 7/12/14.
 */
public enum TokenType {

    OPERATION("operation"), OPERAND("operand");

    private String tokenType;

    private TokenType(String s) {
        tokenType = s;
    }

    public String getTokenType() {
        return tokenType;
    }

}
