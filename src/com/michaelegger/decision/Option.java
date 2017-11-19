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
public class Option extends ItemBase implements Comparable<ItemBase> {

    public Option(String name) {
        this.name = name;
    }

    public void increaseValue(double value) {
        this.value = this.value + value;
    }

    @Override
    public int compareTo(ItemBase item2) {
        if (this.value == item2.value) {
            return 0;
        } else if (this.value > item2.value) {
            return -1;
        } else {
            return 1;
        }
    }
}
