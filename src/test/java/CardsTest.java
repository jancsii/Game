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

    Cards cards = Cards.getInstanceCards();

    LinkedList<Boolean> goal = new LinkedList<>();

    LinkedList<Integer> max = new LinkedList<>();

    private int tempMax = 0;

    public String testgetCardColor(String id) {
        switch (id) {
            case "btn1":
                return "yellow";
            case "btn2":
                return "red";
            case "btn3":
                return "blue";
            case "btn4":
                return "green";
            case "btn5":
                return "orange";
            case "btn6":
                return "turquoise";
            case "btn7":
                return "pink";
            case "btn8":
                return "purple";
            case "btn9":
                return "green";
            case "btn10":
                return "orange";
            case "btn11":
                return "yellow";
            case "btn12":
                return "pink";
            case "btn13":
                return "turquoise";
            case "btn14":
                return "blue";
            case "btn15":
                return "purple";
            case "btn16":
                return "pink";
        }
        return "";
    }

    @Test
    public void testtesting() {
        assertEquals(testgetCardColor("btn5"), testgetCardColor("btn10"));
        assertEquals(testgetCardColor("btn15"), testgetCardColor("btn8"));
    }

    public void load() {

        for (int i = 0; i < 14; i++) {
            goal.add(true);
        }

    }

    @Test
    public void testisGoal() {
        load();
        assertEquals(true, cards.isGoal(goal));
    }

    @Test
    public void testmaxScore() {
        max.add(14);
        max.add(45);
        max.add(68);
        max.add(74);
        for (int j = 0; j < max.size(); j++) {
            if (max.get(j) > tempMax) {
                tempMax = max.get(j);
            }

        }
        assertEquals(74, tempMax);
    }

    @Test
    public void testinit() {
        cards.init();
        assertEquals(16, cards.colors.size());
    }

    @Test
    public void testgetCardC() {
        assertEquals(cards.playingCards[2][3].getColor(), cards.getCardC(2, 3));
    }

    @Test
    public void testgetColor() {
        assertEquals(cards.playingCards[0][0].getColor(), cards.getCardColor("btn1"));
        assertEquals(cards.playingCards[1][2].getColor(), cards.getCardColor("btn7"));
    }

    @Test
    public void testgetChecked() {
        assertEquals(cards.playingCards[1][0].isCheck(), cards.getChecked("btn5"));
        assertEquals(cards.playingCards[3][0].isCheck(), cards.getChecked("btn13"));
    }

    @Test
    public void testgetCard() {
        assertEquals(cards.playingCards[0][3], cards.getCard("btn4"));
        assertEquals(cards.playingCards[2][2], cards.getCard("btn11"));
    }

}
