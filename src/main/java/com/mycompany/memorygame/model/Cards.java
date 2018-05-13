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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author jancsi
 */
public class Cards {
        
        /**
        * Number of total pairs of a {@link Cards} object. 
        */
        private int totalPairs;
        
        /**
        * Number of correct pairs of a {@link Cards} object. 
        */
        private int correctPairs;
        
        /**
        * Accuracy of a player's attempts. 
        */
        private double accuracy;
        
        /**
        * Score of a player during a match. 
        */
        private int score;
        
        /**
        * A 4x4 array, which elements are {@link Card}s.
        */
        public Card kartya[][] = new Card[4][4];
        
        /**
        * A list which contains colors.
        */
        public ArrayList<String> colors = new ArrayList<> ();
        
        /**
        * The best score of all so far. 
        */
        private int max = 0;
        
        /**
        * When a {@link Cards} object is created, this method uploads the list.
        */
        public final void init()
        {  
            for(int f=0; f<2; f++)
            {
                colors.add("kek");
                colors.add("piros");
                colors.add("sarga");
                colors.add("lila");
                colors.add("turkiz");
                colors.add("narancs");
                colors.add("zold");
                colors.add("pink");
            }
        }
        
        /**
        * Creates an object of class {@link Cards}.
        * <p>
        * Creates {@link Card} objects, and random generate color to {@link Card}.
        * Set the totalPairs, correctPairs, accuracy, score to 0.
        * </p>
        */
        private Cards()
        {
            init();

            Random random = new Random();
            
            for(int d=0; d<4; d++)
            {
                for(int e=0; e<4; e++)
                {
                    int index = random.nextInt(colors.size());
                    kartya[d][e] = new Card(colors.get(index),true);
                    colors.remove(index);
                }
            }
            this.totalPairs = 0;
            this.correctPairs = 0;
            this.accuracy = 0;
            this.score = 0;
                                
        }
        
        /**
        * A {@link Cards} type instance.
        */
        private static Cards instance;
    
        /**
        * Returns the instance of the {@link Cards} class.
        * 
        * <p>
        * If there is no instance then create one new, if already exists then passes that.
        * </p>
        * 
        * @return a {@link Cards} object
        */
        public static Cards getPeldany()
        {
            if(instance == null)
                instance = new Cards();
            return instance;
        }
        
        /**
        * Returns the desired {@link Card} color.
        * 
        * @param i row index
        * @param j column index
        * @return a string, which is the color of the {@link Card} with given parameters
        */
        public String getKartya(int i, int j)
        {
            return kartya[i][j].getColor();
        }
        
        /**
        * Returns the desired {@link Card} color.
        * 
        * @param id id of a button
        * @return a string, which is the color of the {@link Card}
        */
        public String getSzin(String id)
        {
            switch (id) {
            case "btn1":  
                    return kartya[0][0].getColor();
            case "btn2":  
                    return kartya[0][1].getColor();
            case "btn3":  
                    return kartya[0][2].getColor();
            case "btn4":  
                    return kartya[0][3].getColor();
            case "btn5":  
                    return kartya[1][0].getColor();
            case "btn6":  
                    return kartya[1][1].getColor();
            case "btn7":  
                    return kartya[1][2].getColor();
            case "btn8":  
                    return kartya[1][3].getColor();
            case "btn9":  
                    return kartya[2][0].getColor();
            case "btn10":  
                    return kartya[2][1].getColor();
            case "btn11":  
                    return kartya[2][2].getColor();
            case "btn12":  
                    return kartya[2][3].getColor();
            case "btn13":  
                    return kartya[3][0].getColor();
            case "btn14":  
                    return kartya[3][1].getColor();
            case "btn15":  
                    return kartya[3][2].getColor();
            case "btn16":  
                    return kartya[3][3].getColor();
            }
            return "";
        }
        
        /**
        * Returns whether it is checked.
        * 
        * @param id id of a button
        * @return that the Card is checked 
        */
        public boolean getChecked(String id)
        {
            switch (id) {
            case "btn1":  
                    return kartya[0][0].isCheck();
            case "btn2":  
                    return kartya[0][1].isCheck();
            case "btn3":  
                    return kartya[0][2].isCheck();
            case "btn4":  
                    return kartya[0][3].isCheck();
            case "btn5":  
                    return kartya[1][0].isCheck();
            case "btn6":  
                    return kartya[1][1].isCheck();
            case "btn7":  
                    return kartya[1][2].isCheck();
            case "btn8":  
                    return kartya[1][3].isCheck();
            case "btn9":  
                    return kartya[2][0].isCheck();
            case "btn10":  
                    return kartya[2][1].isCheck();
            case "btn11":  
                    return kartya[2][2].isCheck();
            case "btn12":  
                    return kartya[2][3].isCheck();
            case "btn13":  
                    return kartya[3][0].isCheck();
            case "btn14":  
                    return kartya[3][1].isCheck();
            case "btn15":  
                    return kartya[3][2].isCheck();
            case "btn16":  
                    return kartya[3][3].isCheck();
            }
            return false;
        }
        
        /**
        * Returns a {@link Card}. 
        * 
        * @param id id of a button
        * @return a {@link Card}  
        */
         public Card getCard(String id)
        {
            switch (id) {
            case "btn1":  
                    return kartya[0][0];
            case "btn2":  
                    return kartya[0][1];
            case "btn3":  
                    return kartya[0][2];
            case "btn4":  
                    return kartya[0][3];
            case "btn5":  
                    return kartya[1][0];
            case "btn6":  
                    return kartya[1][1];
            case "btn7":  
                    return kartya[1][2];
            case "btn8":  
                    return kartya[1][3];
            case "btn9":  
                    return kartya[2][0];
            case "btn10":  
                    return kartya[2][1];
            case "btn11":  
                    return kartya[2][2];
            case "btn12":  
                    return kartya[2][3];
            case "btn13":  
                    return kartya[3][0];
            case "btn14":  
                    return kartya[3][1];
            case "btn15":  
                    return kartya[3][2];
            case "btn16":  
                    return kartya[3][3];
            }
            return new Card("",false);
        }
        
        
        /**
        * Returns two {@link Card}'s color is equal or not.
        * 
        * 
        * @param str1 id of a button
        * @param str2 id of a button
        * @return the cards color is equal or not
        */
        public boolean tesztel(String str1, String str2)
        {         
            return(getSzin(str1).equals(getSzin(str2)));
        }
        
        /**
        * Set the Card's check like the parameter.
        * 
        * @param id id of a button
        * @param check is checked the Card or not
        */
        public void setCheck2(String id, boolean check)
        {
            getCard(id).setCheck(check);       
        }

         //boolean tempGoal = true;
        /**
        * Returns that all the {@link Card} pairs are found. 
        * 
        * @param goals list of the found {@link Card} pairs;
        * @return all the {@link Card}s are found or not
        */
        public boolean isGoal(LinkedList<Boolean> goals)
        {
            return goals.size()==14;     
        }
        
        /**
        * Increments the totalPairs of the {@link Cards} with 1.
        */
        public void totalPairsCounter()
        {
            this.totalPairs++;
        }
        
        /**
        * Increments the correctPairs of the {@link Cards} with 1.
        */
        public void correctPairsCounter()
        {
            this.correctPairs++;
        }
        
        /**
        * Returns the totalPairs of {@link Cards}.
        * 
        * @return totalPairs of {@link Cards}
        */
        public int getTotalPairs()
        {
            return this.totalPairs + 1;
        }
        
        /**
        * Returns the correctPairs of {@link Cards}.
        * 
        * @return correctPairs of {@link Cards}
        */
        public int getCorrectPairs()
        {
            return this.correctPairs + 1;
        }
        
        /**
        * Calculate and set the accuracy of {@link Cards}.
        */
        private void setAccuracy()
        {
            this.accuracy = getCorrectPairs() * 100 / getTotalPairs();
            this.accuracy = Math.round (accuracy * 10.0) / 10.0;
        }
        
        /**
        * Returns the accuracy of {@link Cards}.
        * 
        * @return accuracy of {@link Cards}
        */
        public double getAccuracy()
        {
            setAccuracy();
            return this.accuracy;
        }
        
        /**
        * Increments the score of the {@link Cards} with 5.
        */
        public void scoreInc()
        {
            this.score = this.score + 5;
        }
        
        /**
        * Decreases the score of the {@link Cards} with 2.
        */
        public void scoreDec()
        {
            this.score = this.score - 2;
        }
        
        /**
        * Returns the score of {@link Cards}.
        * 
        * @return score of {@link Cards}
        */
        public int getScore()
        {    
            return this.score +5;
        }
        
        /**
        * Calculate the max score of all players score.
        * 
        * @param goals list of all players score
        */
        public void maxScore(LinkedList<Integer> goals)
        {
            
            for(int j=0; j<goals.size(); j++)
            {
                if(goals.get(j) > max)
                    max = goals.get(j);
                    
            }
        }
        
        /**
        * Returns the max score.
        * 
        * @return the max score
        */
        public int getMax()
        {
            return max;
        }
        
        /**
        * Returns that the actual player's score is greater than the max score.
        * 
        * @return the actual player's score is greater than the max score
        */
        public boolean newMaxScore()
        {
            return getScore() > max;
                
        }
        
        /**
        * Returns {@link Cards} object in readable form.
        * 
        * @return readable form of {@link Cards} object
        */
    @Override
    public String toString() {
        return "Cards{" + "totalPairs=" + totalPairs + ", correctPairs=" + correctPairs + ", accuracy=" + accuracy + ", score=" + score + ", kartya=" + kartya + ", colors=" + colors + ", max=" + max + '}';
    }
        
        
        
}
