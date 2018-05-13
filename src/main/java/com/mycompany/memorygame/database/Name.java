/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memorygame.database;

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
 * Contains the login name of the player.
 */
public class Name {
    private String namefield;
    
    /**
    * Constructor of Name class.
    */
    public Name()
    {
        
    }

    //public Name(String namefield) {
    //    this.namefield = namefield;
    //}
    
    /**
    * Returns the player's name.
    *
    * @return name of the player
    */
    public String getNamefield() {
        return namefield;
    }
    
    /**
    * Sets the player's name.
    *
    * @param namefield is the name we want to set to player
    */
    public void setNamefield(String namefield) {
        this.namefield = namefield;
    }
    
    
}
