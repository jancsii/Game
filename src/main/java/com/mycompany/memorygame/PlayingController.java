package com.mycompany.memorygame;


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

public class PlayingController implements Initializable {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayingController.class);
    
    Cards cards = new Cards();
    
    LinkedList<Button> buttons = new LinkedList<>();
    
    ArrayList<Button> blacks = new ArrayList<>();
    
    LinkedList<Boolean> goals = new LinkedList<>();
    
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
    
    public void onClick()
    {   
        pane.getChildren().stream().forEach(e -> e.setOnMouseClicked(b -> change(b)));
    }
    
    boolean change;
    
    Database database = Database.getPeldany();
    
    @FXML
    private void change(MouseEvent event)
    {
        String temp = megnyomva();
        Button b = (Button) event.getSource();
        b.setStyle("-fx-background-image: url('/images/img1/" + temp + ".png')");
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
                    LOGGER.info("Vege...");
                    LOGGER.info("Total: " + cards.getTotalPairs());
                    LOGGER.info("Accuracy: " + cards.getAccuracy() + " %");
                    LOGGER.info("Score: " + cards.getScore());
                    
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
    
    public void setImages()
    {
        LOGGER.info("Loading the start position.");
        
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
        //database.read();
        //label.setText(Integer.toString(cards.getMax()));
    }    
}
