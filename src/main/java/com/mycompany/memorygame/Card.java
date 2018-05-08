/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memorygame;

/**
 *
 * @author jancsi
 */
public class Card {
    
    private String color;
    private boolean check;
    
    public Card(String color, boolean check)
    {
        this.color = color;
        this.check = check;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
    
}
