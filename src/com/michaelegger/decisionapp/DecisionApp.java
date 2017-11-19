/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.michaelegger.decisionapp;

import com.michaelegger.decision.Category;
import com.michaelegger.decision.Microdecision;
import com.michaelegger.decision.Option;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 *
 * @author Mike
 */
public class DecisionApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Decision decision = new Decision();

        InputStreamReader stream = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(stream);

        String input;

        try {
            System.out.println("Please enter the name of your decision:");
            input = buffer.readLine();

            decision.name = input;

            System.out.println("Please enter the names of your options. One option per line:\n");
            input = buffer.readLine();
            while (input.isEmpty() == false) {
                decision.addOption(new Option(input));
                input = buffer.readLine();
            }

            System.out.println("Please enter the categories you want to use. One category per line:\n");
            input = buffer.readLine();
            while (input.isEmpty() == false) {
                decision.addCategory(new Category(input));
                input = buffer.readLine();
            }

            boolean done = false;
            while (done == false) {
                Microdecision current = decision.getNextDecision();
                if (current != null) {
                    if (current.getItemCategory() == null) {
                        System.out.println("Please choose the category that is more important for you: ");
                        chooseWinner(buffer, current);
                    } else {
                        System.out.println("Please choose the better option in the category: " + current.getItemCategory().getName());
                        chooseWinner(buffer, current);
                    }
                } else {
                    done = true;
                    System.out.println("Well done! Calculating your decision...");

                    for (Option option : decision.getWinners(5)) {
                        System.out.println("Option: " + option.getName() + " Points: " + option.getValue());
                    }
                    
                    System.out.println("The best option for you is: " + decision.getWinner().getName());
                    
                }
            }
            System.out.println("");

        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    static public void chooseWinner(BufferedReader buffer, Microdecision current) throws IOException, Exception {
        String input;
        System.out.println("(A) " + current.getItemA().getName());
        System.out.println("(B) " + current.getItemB().getName());

        boolean validinput = false;
        while (validinput == false) {
            input = buffer.readLine();
            switch (input) {
                case "a":
                case "A":
                    current.setWinner(current.getItemA());
                    validinput = true;
                    break;
                case "b":
                case "B":
                    current.setWinner(current.getItemB());
                    validinput = true;
                    break;
                default:
            }
        }
    }

}
