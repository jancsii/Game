/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memorygame.model;

/*-
 * #%L
 * MemoryGame
 * %%
 * Copyright (C) 2018 Faculty of Informatics, University of Debrecen
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
/**
 *
 * @author jancsi
 */
public class Card {

    private String color;
    private boolean check;

    /**
     * Creates a {@link Card} object with given parameters, which will be a card
     * in the game.
     *
     * @param color which is random and that's will be the {@link Card}'s color
     * @param check at the start will be true, because you can turn the
     * {@link Card}
     */
    public Card(String color, boolean check) {
        this.color = color;
        this.check = check;
    }

    /**
     * Returns the color of a {@link Card} object.
     *
     * @return the object's color
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns that if a card is checked or not.
     *
     * @return the {@link Card} is upside-down
     */
    public boolean isCheck() {
        return check;
    }

    /**
     * Set the object's check field to the given parameter.
     *
     * @param check true if we can check the {@link Card} and false if we can't
     * because already checked
     */
    public void setCheck(boolean check) {
        this.check = check;
    }

}
