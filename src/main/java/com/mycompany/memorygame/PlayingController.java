package com.mycompany.memorygame;

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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* FXML Controller class. 
*/
public class PlayingController implements Initializable {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayingController.class);

    Cards cards; 
    
    LinkedList<Button> buttons = new LinkedList<>();
    
    ArrayList<Button> blacks = new ArrayList<>();
    
    LinkedList<Boolean> goals = new LinkedList<>();
    
    boolean change;
    
    Database database;
    
    int j = 0;
    
    @FXML
    private Label label;
    
    @FXML
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16;
    
    @FXML
    private Pane pane;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }
    
    /**
    * To button click returns which {@link Card} corresponds to that button.
    * 
    * @return {@link Card}'s color
    */
    public String megnyomva()
    {
        if(btn1.isHover())
        {
            return cards.getKartya(0,0);
        }
        else{
            if(btn2.isHover())
            {
                return cards.getKartya(0,1);
            }
            else{
            if(btn3.isHover())
            {
                return cards.getKartya(0,2);
            }
            else{
            if(btn4.isHover())
            {
                return cards.getKartya(0,3);
            }
            else{
            if(btn5.isHover())
            {
                return cards.getKartya(1,0);
            }
            else{
            if(btn6.isHover())
            {
                return cards.getKartya(1,1);
            }
            else{
            if(btn7.isHover())
            {
                return cards.getKartya(1,2);
            }
            else{
            if(btn8.isHover())
            {
                return cards.getKartya(1,3);
            }
            else{
            if(btn9.isHover())
            {
                return cards.getKartya(2,0);
            }
            else{
            if(btn10.isHover())
            {
                return cards.getKartya(2,1);
            }
            else{
            if(btn11.isHover())
            {
                return cards.getKartya(2,2);
            }
            else{
            if(btn12.isHover())
            {
                return cards.getKartya(2,3);
            }
            else{
            if(btn13.isHover())
            {
                return cards.getKartya(3,0);
            }
            else{
            if(btn14.isHover())
            {
                return cards.getKartya(3,1);
            }
            else{
            if(btn15.isHover())
            {
                return cards.getKartya(3,2);
            }
            else{
            if(btn16.isHover())
            {
                return cards.getKartya(3,3);
            }
        }
        }
        }
        }
        }
        }
        }
        }
        }
        }
        }
        }
        }
        }
        }
        
        return null;
    }
    
    /**
    * Method to all button that we can check the {@link Card}s.
    */
    public void onClick()
    {   
        pane.getChildren().stream().forEach(e -> e.setOnMouseClicked(b -> change(b)));
    }
    
    @FXML
    private void change(MouseEvent event)
    {
        String temp = megnyomva();
        Button b = (Button) event.getSource();
        b.setStyle("-fx-background-image: url('/images/img1/" + temp + ".png')");
        //Image img1 = new Image(getClass().getClassLoader().getResourceAsStream("/images/img1/" + temp + ".png"));
        //ImageView iv1 = new ImageView(img1);
        //b.setStyle("/images/img1/" + temp + ".png");
        String tempId = b.getId();
        boolean tempCheck;
        tempCheck = cards.getChecked(tempId);
        
        try
        {      
            if(buttons.size() < 2 && tempCheck==true)
            {
                buttons.addLast(b);
                
                cards.setCheck2(tempId, false);      
            }
            else if(buttons.size() == 2 && tempCheck==true)
            {   
                LOGGER.info("Checking cards: {} and {}", buttons.get(0).getId(), buttons.get(1).getId());
                change = cards.tesztel(buttons.get(0).getId(), buttons.get(1).getId());
                if(!change)
                {
                    buttons.get(0).setStyle("-fx-background-image: url('/images/img1/fekete.png')");
                    buttons.get(1).setStyle("-fx-background-image: url('/images/img1/fekete.png')");
                    cards.setCheck2(buttons.get(0).getId(), true);
                    cards.setCheck2(buttons.get(1).getId(), true);  
                    
                    cards.totalPairsCounter();
                    cards.scoreDec();
                }
                else
                {
                    buttons.get(0).setDisable(true);
                    buttons.get(1).setDisable(true);
                    
                    goals.add(cards.getChecked(buttons.get(0).getId()));
                    goals.add(cards.getChecked(buttons.get(1).getId()));
                    
                    cards.totalPairsCounter();
                    cards.correctPairsCounter();
                    cards.scoreInc();
                }
 
                buttons.clear();
                
                buttons.addLast(b);
                
                cards.setCheck2(tempId, false);
            }
            else
            {
                if(buttons.get(1) != null && cards.isGoal(goals))
                {
                    buttons.get(0).setDisable(true);
                    buttons.get(1).setDisable(true);
                    LOGGER.debug("Vege...");
                    LOGGER.info("Total: " + cards.getTotalPairs());
                    LOGGER.info("Accuracy: " + cards.getAccuracy() + " %");
                    LOGGER.info("Score: " + cards.getScore());
                    
                    if(cards.newMaxScore())
                    {    
                        label.setText("Rekord: " + String.valueOf(cards.getScore()) + " pont");
                        LOGGER.info("Congratulations, New Record: " + cards.getScore());
                    }
                    else
                        LOGGER.info("Good job, your score: " + cards.getScore());
                    
                    try
                    {
                        
                        //database.read();
                        database.getCards(cards);
                        database.init();
                        database.concatenateXML();
            
                    }catch(Exception e)
                    {   
                        LOGGER.error(e.getMessage());
                    }
                }
            }
      
        }
        catch(Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        
    }
    
    /**
    * At the start of the game sets all button's image to starting image.
    */
    public void setImages()
    {
        LOGGER.debug("Loading the start position.");
        
        blacks.add(btn1);
        blacks.add(btn2);
        blacks.add(btn3);
        blacks.add(btn4);
        blacks.add(btn5);
        blacks.add(btn6);
        blacks.add(btn7);
        blacks.add(btn8);
        blacks.add(btn9);
        blacks.add(btn10);
        blacks.add(btn11);
        blacks.add(btn12);
        blacks.add(btn13);
        blacks.add(btn14);
        blacks.add(btn15);
        blacks.add(btn16);
        
        for(Button blackb : blacks)
        {
            blackb.setStyle("-fx-background-image: url('/images/img1/fekete.png')");
        }
        

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Database database = Database.getPeldany();
        onClick();
        setImages();
        
        cards = Cards.getPeldany();
        database = Database.getPeldany();
        System.out.println(database);
        database.read();
        label.setText("Rekord: " + String.valueOf(cards.getMax()) + " pont");
    }
        
}
