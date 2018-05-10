/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.memorygame.Cards;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jancsi
 */
public class CardsTest {
    
    Cards cards = Cards.getPeldany();
    
    LinkedList<Boolean> g = new LinkedList<>();
    
    @Test
    public void testtesztel()
    {
        assertTrue("0",cards.tesztel("alma", "korte"));
        assertTrue("1",cards.tesztel("alma", "alma"));
    }
    
    public void init()
    {        
        
        for(int i=0; i<14; i++)
            g.add(true);
        
    }
    
    @Test
    public void testisGoal()
    {
        init();
        assertEquals(true,cards.isGoal(g));
    }
    
}
