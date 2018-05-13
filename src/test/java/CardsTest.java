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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.memorygame.model.Cards;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jancsi
 */
public class CardsTest {
    
    Cards cards = Cards.getPeldany();
    
    LinkedList<Boolean> goal = new LinkedList<>();
    
    LinkedList<Integer> max = new LinkedList<>();
    
    private int tempMax = 0;
    
    public String testgetSzin(String id)
        {
            switch (id) {
            case "btn1":  
                    return "sarga";
            case "btn2":  
                    return "piros";
            case "btn3":  
                    return "szurke";
            case "btn4":  
                    return "zold";
            case "btn5":  
                    return "narancs";
            case "btn6":  
                    return "turkiz";
            case "btn7":  
                    return "pink";
            case "btn8":  
                    return "lila";
            case "btn9":  
                    return "zold";
            case "btn10":  
                    return "narancs";
            case "btn11":  
                    return "sarga";
            case "btn12":  
                    return "pink";
            case "btn13":  
                    return "turkiz";
            case "btn14":  
                    return "szurke";
            case "btn15":  
                    return "lila";
            case "btn16":  
                    return "pink";
            }
            return "";
        }
    
    @Test
    public void testtesztel()
    {
        assertEquals(testgetSzin("btn5"),testgetSzin("btn10"));
        assertEquals(testgetSzin("btn15"),testgetSzin("btn8"));
    }
    
    public void load()
    {        
        
        for(int i=0; i<14; i++)
            goal.add(true);
        
    }
    
    @Test
    public void testisGoal()
    {
        load();
        assertEquals(true,cards.isGoal(goal));
    }
    
    @Test
    public void testmaxScore()
    {
        max.add(14);
        max.add(45);
        max.add(68);
        max.add(74);
        for(int j=0; j<max.size(); j++)
        {
            if(max.get(j) > tempMax)
                tempMax = max.get(j);
                    
        }
        assertEquals(74,tempMax);
    }
    
    @Test
    public void testinit()
    {  
        cards.init();
        assertEquals(16,cards.colors.size());
    }
    
    @Test
    public void testgetKartya()
    {
        assertEquals(cards.kartya[2][3].getColor(),cards.getKartya(2,3));
    }
    
    @Test
    public void testgetSzin()
    {
        assertEquals(cards.kartya[0][0].getColor(),cards.getSzin("btn1"));
        assertEquals(cards.kartya[1][2].getColor(),cards.getSzin("btn7"));
    }
    
    @Test
    public void testgetChecked()
    {
        assertEquals(cards.kartya[1][0].isCheck(),cards.getChecked("btn5"));
        assertEquals(cards.kartya[3][0].isCheck(),cards.getChecked("btn13"));
    }
    
    @Test
    public void testgetCard()
    {
        assertEquals(cards.kartya[0][3],cards.getCard("btn4"));
        assertEquals(cards.kartya[2][2],cards.getCard("btn11"));
    }
    
}
