package com.pavlovmedia;

import com.pavlovmedia.api.parser.Expression;
import com.pavlovmedia.impl.Calculator;
import com.pavlovmedia.impl.parser.MathExpression;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RPN Calculator application
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application
{
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);
        ApplicationContext context = app.run(args);
        Calculator calculator = (Calculator) context.getBean("calculator");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n-------- Calculator Started ---------\n");

        while (true) {
            try{
                System.out.println("\nEnter an RPN-formatted expression to evaluate: ");
                String expValue = in.readLine();

                if ("q".equalsIgnoreCase(expValue) || "quit".equalsIgnoreCase(expValue)) {
                    break;
                }

                Double result = calculator.evaluate(expValue);

                if (result != null) {
                    System.out.println("\nexpression is evaluated to a value = " + result.doubleValue());
                } else {
                    System.out.println("\nPlease review the entered expression format");
                }
            }
            catch (Exception e) {
                System.out.println("\nCaught an exception. Description: " + e.getMessage());
            }

            System.out.println("\n-----------------\n");
        }

        System.out.println("\n-------- Terminated ---------\n");
    }
}
