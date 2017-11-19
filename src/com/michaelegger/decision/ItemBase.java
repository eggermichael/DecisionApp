/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.michaelegger.decision;

/**
 *
 * @author Mike
 */
public abstract class ItemBase {

    protected String name;
    protected Double value = 0.0;

    public String getName() {
        return name;
    }

    public void increaseValue() {
        value = value + 1.0;
    }

    public Double getValue() {
        return value;
    }


}

