package com.pavlovmedia.adhoc;

/**
 * Created by hmohamed on 7/12/14.
 *
 * for trials only: Trying out regular expression
 * not in use in the calculator code.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegularExpression {

    public static void main(String[] args) {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try{
                System.out.println("Enter your regex: ");
                String regExStr = in.readLine();
                Pattern pattern = Pattern.compile(regExStr);

                System.out.println("\nEnter input string to search: ");
                String inputStr = in.readLine();
                Matcher matcher = pattern.matcher(inputStr);

                boolean found = false;
                while (matcher.find()) {
                    System.out.println("\nI found the text " + matcher.group()
                            + " starting at index "
                            + matcher.start() + " and ending at " + matcher.end());
                    found = true;
                }
                if (!found) {
                    System.out.println("\nNo match found.");
                }
            }
            catch (Exception e) {
                System.out.println("\nCaught an exception. Description: " + e.getMessage());
            }
            System.out.println("---------");
        }
    }
}

