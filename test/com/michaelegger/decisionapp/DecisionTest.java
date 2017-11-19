/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.michaelegger.decisionapp;

import com.michaelegger.decision.Category;
import com.michaelegger.decision.Microdecision;
import com.michaelegger.decision.Option;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mike
 */
public class DecisionTest {

    public DecisionTest() {
    }

    static private Decision decision;

    @BeforeClass
    public static void setUpClass() {
        decision = new Decision();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        decision = new Decision();
        decision.name = "Test Decision";
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addCategory method, of class Decision.
     */
    @Test
    public void testAddCategory() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field fcategory = Decision.class.getDeclaredField("categories");
        fcategory.setAccessible(true);
        System.out.println("addCategory");

        ArrayList<Category> categories = (ArrayList<Category>) fcategory.get(decision);
        assertEquals(categories.size(), 0);

        Category category = new Category("Category 1");
        decision.addCategory(category);

        categories = (ArrayList<Category>) fcategory.get(decision);
        assertEquals(categories.get(0).getName().compareTo("Category 1"), 0);
        assertEquals(categories.size(), 1);

        category = new Category("Category 2");
        decision.addCategory(category);

        categories = (ArrayList<Category>) fcategory.get(decision);
        assertEquals(categories.get(1).getName().compareTo("Category 2"), 0);
        assertEquals(categories.size(), 2);

    }

    /**
     * Test of addOption method, of class Decision.
     */
    @Test
    public void testAddOption() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field foptions = Decision.class.getDeclaredField("options");
        foptions.setAccessible(true);
        System.out.println("addOption");

        ArrayList<Option> options = (ArrayList<Option>) foptions.get(decision);
        assertEquals(options.size(), 0);

        Option option = new Option("Option 1");
        decision.addOption(option);

        options = (ArrayList<Option>) foptions.get(decision);
        assertEquals(options.get(0).getName().compareTo("Option 1"), 0);
        assertEquals(options.size(), 1);

        option = new Option("Option 2");
        decision.addOption(option);

        options = (ArrayList<Option>) foptions.get(decision);
        assertEquals(options.get(1).getName().compareTo("Option 2"), 0);
        assertEquals(options.size(), 2);
    }

    /**
     * Test of getNextDecision method, of class Decision.
     */
    @Test
    public void testGetNextDecision() throws Exception {
        System.out.println("getNextDecision");
        decision.addCategory(new Category("Category 1"));
        decision.addCategory(new Category("Category 2"));
        decision.addOption(new Option("Option 1"));
        decision.addOption(new Option("Option 2"));

        Microdecision result = decision.getNextDecision();
        assertEquals(result.getItemA().getName().compareTo("Category 1"), 0);
        assertEquals(result.getItemB().getName().compareTo("Category 2"), 0);
        result.setWinner(result.getItemA());

        result = decision.getNextDecision();
        assertEquals(result.getItemA().getName().compareTo("Option 1"), 0);
        assertEquals(result.getItemB().getName().compareTo("Option 2"), 0);
        assertEquals(result.getItemCategory().getName().compareTo("Category 1"), 0);
        result.setWinner(result.getItemA());

        result = decision.getNextDecision();
        assertEquals(result.getItemA().getName().compareTo("Option 1"), 0);
        assertEquals(result.getItemB().getName().compareTo("Option 2"), 0);
        assertEquals(result.getItemCategory().getName().compareTo("Category 2"), 0);
        result.setWinner(result.getItemA());

        result = decision.getNextDecision();
        assertEquals(result, null);
    }

    /**
     * Test of getWinners method, of class Decision.
     */
    @Test
    public void testGetWinners() throws Exception {
        System.out.println("getWinners");
        int len = 2;

        decision.addCategory(new Category("Category 1"));
        decision.addCategory(new Category("Category 2"));
        decision.addOption(new Option("Option 1"));
        decision.addOption(new Option("Option 2"));

        Microdecision microdecision = decision.getNextDecision();
        microdecision.setWinner(microdecision.getItemA());

        microdecision = decision.getNextDecision();
        microdecision.setWinner(microdecision.getItemA());

        microdecision = decision.getNextDecision();
        microdecision.setWinner(microdecision.getItemB());

        microdecision = decision.getNextDecision();
        assertEquals(microdecision, null);

        ArrayList<Option> winners = decision.getWinners(len);

        System.out.println(winners.get(0).getName() + " " + winners.get(0).getValue());
        System.out.println(winners.get(1).getName() + " " + winners.get(1).getValue());

        assertEquals(winners.size(), len);
        assertEquals(winners.get(0).getValue(), 2.0, 0.01);
        assertEquals(winners.get(1).getValue(), 1.0, 0.01);

    }

    /**
     * Test of getWinner method, of class Decision.
     */
    @Test
    public void testGetWinner() throws Exception {
        System.out.println("getWinner");
        decision.addCategory(new Category("Category 1"));
        decision.addCategory(new Category("Category 2"));
        decision.addOption(new Option("Option 1"));
        decision.addOption(new Option("Option 2"));
        Microdecision microdecision = decision.getNextDecision();
        microdecision.setWinner(microdecision.getItemA());
        microdecision = decision.getNextDecision();
        microdecision.setWinner(microdecision.getItemA());
        microdecision = decision.getNextDecision();
        microdecision.setWinner(microdecision.getItemB());
        microdecision = decision.getNextDecision();
        assertEquals(microdecision, null);

        Option result = decision.getWinner();

        assertEquals("Option 1", result.getName());
        assertEquals(result.getValue(), 2.0, 0.01);
    }

}
