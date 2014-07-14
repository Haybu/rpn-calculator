package com.pavlovmedia;

import com.pavlovmedia.api.parser.Expression;
import com.pavlovmedia.impl.Calculator;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * Integration test for RPN calculator Application.
 */

@Profile("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {com.pavlovmedia.Application.class})
public class ExpressionTest
{

    @Autowired
    Calculator calculator;

    @org.junit.Test
    public void testValidExpression1() {
        String expValue = "2 3 4 8 5 + - * /";
        Double result = calculator.evaluate(expValue);
        Assert.notNull(result, "Expression " + expValue + " is supposed to be valid");
    }


    @org.junit.Test
    public void testValidExpression2() {
        String expValue = "7 8 9 200 + - *";
        Double result = calculator.evaluate(expValue);
        Assert.notNull(result, "Expression " + expValue + " is supposed to be valid");
    }

    @org.junit.Test
    public void testValidExpression3() {
        String expValue = "12 13 140 + -";
        Double result = calculator.evaluate(expValue);
        Assert.notNull(result, "Expression " + expValue + " is supposed to be valid");
    }

    @org.junit.Test
    public void testInvalidExpressionWithMoreOperators() {
        String expValue = "21 31 + -";
        Double result = calculator.evaluate(expValue);
        Assert.isNull(result, "Expression " + expValue + " not supposed to be valid");
    }

    @org.junit.Test
    public void testInvalidExpressionWithLessOperators() {
        String expValue = "22 33 420 +";
        Double result = calculator.evaluate(expValue);
        Assert.isNull(result, "Expression " + expValue + " not supposed to be valid");
    }

    @org.junit.Test
    public void testInvalidExpressionWithExtraOperands() {
        String expValue = "112 113 340 + - *";
        Double result = calculator.evaluate(expValue);
        Assert.isNull(result, "Expression " + expValue + " not supposed to be valid");
    }

    @org.junit.Test
    public void testInvalidMalformedExpression() {
        String expValue = "+ 24 36 42 + - *";
        Double result = calculator.evaluate(expValue);
        Assert.isNull(result, "Expression " + expValue + " not supposed to be valid");
    }

    @org.junit.Test
    public void testInvalidExpressionWithUnknownOperator() {
        String expValue = "+ 18 28 48 + - &";
        Double result = calculator.evaluate(expValue);
        Assert.isNull(result, "Expression " + expValue + " not supposed to be valid");
    }


}
