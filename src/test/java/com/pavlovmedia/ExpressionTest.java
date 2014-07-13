package com.pavlovmedia;

import com.pavlovmedia.api.parser.Expression;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * Unit test for calculator Application.
 */

@Profile("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {com.pavlovmedia.Application.class})
public class ExpressionTest
{

    @Autowired
    Expression expression;

    @org.junit.Test
    public void testValidExpression1() {
        String expValue = "2 3 40 20 5 + - * /";
        expression.setExpression(expValue);
        expression.configure();
        Assert.isTrue(expression.isValid(), "Expression " + expValue + " not supposed to be invalid");
    }


    @org.junit.Test
    public void testValidExpression2() {
        String expValue = "7 8 9 200 + - *";
        expression.setExpression(expValue);
        expression.configure();
        Assert.isTrue(expression.isValid(), "Expression " + expValue + " not supposed to be invalid");
    }

    @org.junit.Test
    public void testValidExpression3() {
        String expValue = "12 13 140 + -";
        expression.setExpression(expValue);
        expression.configure();
        Assert.isTrue(expression.isValid(), "Expression " + expValue + " not supposed to be invalid");
    }

    @org.junit.Test
    public void testInvalidExpressionWithMoreOperators() {
        String expValue = "21 31 + -";
        expression.setExpression(expValue);
        expression.configure();
        Assert.isTrue(!expression.isValid(), "Expression " + expValue + " not supposed to be valid");
    }

    @org.junit.Test
    public void testInvalidExpressionWithLessOperators() {
        String expValue = "22 33 420 +";
        expression.setExpression(expValue);
        expression.configure();
        Assert.isTrue(!expression.isValid(), "Expression " + expValue + " not supposed to be valid");
    }

    @org.junit.Test
    public void testInvalidExpressionWithExtraOperands() {
        String expValue = "112 113 340 + - *";
        expression.setExpression(expValue);
        expression.configure();
        Assert.isTrue(!expression.isValid(), "Expression " + expValue + " not supposed to be valid");
    }

    @org.junit.Test
    public void testInvalidMalformedExpression() {
        String expValue = "+ 24 36 42 + - *";
        expression.setExpression(expValue);
        expression.configure();
        Assert.isTrue(!expression.isValid(), "Expression " + expValue + " not supposed to be valid");
    }

    @org.junit.Test
    public void testInvalidExpressionWithUnknownOperator() {
        String expValue = "+ 18 28 48 + - &";
        expression.setExpression(expValue);
        expression.configure();
        Assert.isTrue(!expression.isValid(), "Expression " + expValue + " not supposed to be valid");
    }


}
