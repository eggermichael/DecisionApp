/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.michaelegger.decision;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Mike
 */
public class Decision {

    private final ArrayList<Category> categories = new ArrayList<Category>();
    private ArrayList<Option> options = new ArrayList<Option>();
    private ArrayList<Microdecision> microdecisions = new ArrayList<Microdecision>();
    public String name;
    private Option winner;
    private ArrayList<Option> winners = new ArrayList<Option>();

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void addOption(Option option) {
        this.options.add(option);
    }

    public Microdecision getNextDecision() throws Exception {
        if (this.microdecisions.isEmpty()) {
            createMicrodecisionQueue();
        }
        for (Microdecision microdecision : this.microdecisions) {
            if (microdecision.getWinner() == null) {
                return microdecision;
            }
        }
        return null;
    }

    private void createMicrodecisionQueue() throws Exception {
        if (categories.size() == 0) {
            throw new Exception("Categories list must not be empty.");
        } else if (options.size() == 0) {
            throw new Exception("Opptions list must not be empty.");
        }

        // add category microdecisions
        for (int i = 0; i < categories.size() - 1; i++) {
            for (int j = i + 1; j < categories.size(); j++) {
                microdecisions.add(new Microdecision(categories.get(i), categories.get(j)));
            }
        }
        // add option microdecisions
        for (Category category : categories) {
            for (int i = 0; i < options.size() - 1; i++) {
                for (int j = i + 1; j < options.size(); j++) {
                    microdecisions.add(new Microdecision(options.get(i), options.get(j), category));
                }
            }
        }
    }

    public ArrayList<Option> getWinners(int len) throws Exception {

        if (getNextDecision() == null && !winners.isEmpty()) {
            return winners;
        } else {
            calculateRanking();
        }
        if (len > options.size()) {
            len = options.size();
        }
        winners = new ArrayList<Option>(options.subList(0, len));
        return winners;
    }

    public Option getWinner() throws Exception {
        if (getNextDecision() == null && winner != null) {
            return winner;
        } else {
            calculateRanking();
        }
        winner = options.get(0);
        return winner;
    }

    private void calculateRanking() {
        for (Microdecision micro : microdecisions) {
            if (micro.getItemCategory() == null) {
                micro.getWinner().increaseValue();
            }
        }
        for (Microdecision micro : microdecisions) {
            if (micro.getItemCategory() != null) {
                if (micro.getWinner() instanceof Option) {
                    Option op = (Option) micro.getWinner();
                    op.increaseValue(1 + micro.getItemCategory().getValue());
                }
            }
        }
        Collections.sort(options);
    }
}
