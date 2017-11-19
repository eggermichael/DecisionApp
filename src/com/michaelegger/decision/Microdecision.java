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
public class Microdecision {
    final private ItemBase itemA;
    final private ItemBase itemB;
    final private ItemBase itemCategory;
    private ItemBase winner;

    public Microdecision(ItemBase item_1, ItemBase item_2) {
        this.itemA = item_1;
        this.itemB = item_2;
        this.itemCategory = null;
    }
    
    public Microdecision(ItemBase item_1, ItemBase item_2, ItemBase item_category) {
        this.itemA = item_1;
        this.itemB = item_2;
        this.itemCategory = item_category;
    }
    
    public ItemBase getWinner() {
        return winner;
    }
    
    public void setWinner(ItemBase winner) throws Exception {
        if (winner != itemA && winner != itemB) {
            throw new Exception("Invalid Item.");
        }
        this.winner = winner;
    }
    
    public ItemBase getItemA(){
        return itemA;
    }
    public ItemBase getItemB(){
        return itemB;
    }
    public ItemBase getItemCategory(){
        return itemCategory;
    }
    
}
