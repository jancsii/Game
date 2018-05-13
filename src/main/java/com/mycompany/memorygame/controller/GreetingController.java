package com.mycompany.memorygame.controller;

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

import com.mycompany.memorygame.database.Database;
import com.mycompany.memorygame.database.Name;
import com.mycompany.memorygame.database.NameBuilder;
import com.mycompany.memorygame.database.NameBuilderImpl;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GreetingController implements Initializable {
    
    @FXML
    private Label labelName;
    @FXML
    private Label labelGreeting;
    @FXML
    private TextField textfieldName;
    @FXML
    private Button buttonNext;
    
    public Database db = Database.getPeldany();
    
    public Name construct()
    {
        NameBuilder builder = new NameBuilderImpl();
        return builder.setNamefield(textfieldName.getText()).build();
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Stage stage = (Stage) labelName.getScene().getWindow();
        //Stage stage = new Stage(StageStyle.DECORATED);
        db.setData(construct());

        try
        {
        
        FXMLLoader f1 = new FXMLLoader((getClass().getResource("/fxml/Play.fxml")));
        Parent root = f1.load();
        // Parent root = FXMLLoader.load(getClass().getResource("/fxml/Play.fxml"));
       
     //   f1.<PlayingController>getController().setImages();
       // f1.<PlayingController>getController().onClick();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        
        stage.setTitle("Memory Game");
        stage.setScene(scene);
        stage.show();
        
       // }
        //
      //  catch(IOException e)
        //{
       //     System.out.println("Error :"+e.getMessage());

       // }
        
    }   catch (IOException ex) {
            Logger.getLogger(GreetingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*@FXML
    private void handleButtonAction(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/Play.fxml"));
            Scene scene = new Scene(root);
            Stage stage= new Stage(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.setTitle("Kezdőlap");
            stage.setResizable(false);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(GreetingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    @FXML
    private void goOn(ActionEvent event)
    {
        if(!"".equals(textfieldName.getText()))
        {
            labelGreeting.setMaxWidth(240);
            labelGreeting.setWrapText(true);
            labelGreeting.setText("Welcome " + textfieldName.getText() + "!\nTo start playing, click the button go.");
            
            buttonNext.setVisible(true);
 
        }
        else
        {
            labelGreeting.setMaxWidth(240);
            labelGreeting.setWrapText(true);
            labelGreeting.setText("Enter your name!");
        }
        
        
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
