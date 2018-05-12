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

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* MainApp class starting the JavaFx application.
*/
public class MainApp extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);
    
    /**
    * This method sets the starting position of the game, which is the entry scene.
    * 
    * @param stage is the starting condition
    * @throws java.lang.Exception when can't find the fxml or css file 
    */
    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.trace("Application started!");
        
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/fxml/SignIn.xml"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignIn.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        
        
        
        stage.setTitle("Memory Game");
        stage.setScene(scene);
        stage.show();
        
        
    }

    /**
     * This method calls {@code launch(args)} and then start the JavaFx application.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LOGGER.debug("Loading...");
        launch(args);
    }

}
