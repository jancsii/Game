package com.mycompany.memorygame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
        
        db.setData(construct());
        System.out.println(construct().getNamefield());
        
        try
        {
        
        FXMLLoader f1 = new FXMLLoader(getClass().getResource("/fxml/Play.fxml"));
        
        Parent root = f1.load();
        
        f1.<PlayingController>getController().setImages();
        f1.<PlayingController>getController().onClick();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Memory Game");
        stage.setScene(scene);
        stage.show();
        
        }
        catch(IOException e)
        {
            System.out.println("Error :"+e.getMessage());

        }
        
    }
    
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
