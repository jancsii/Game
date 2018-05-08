/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memorygame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author jancsi
 */
public class Cards {
        
        private int totalPairs;
        private int correctPairs;
        private double accuracy;
        private int score;
        
        Card kartya[][] = new Card[4][4];
        
        ArrayList<String> colors = new ArrayList<> ();
        
        private void init()
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

        public Cards()
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
        
        public String getKartya(int i, int j)
        {
            return kartya[i][j].getColor();
        }
        
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
        
        int i = 0;
        public boolean tesztel(String btn1, String btn2)
        {
            i++;          
            return(getSzin(btn1).equals(getSzin(btn2)));
        }
        
        public void setCheck2(String id, boolean check)
        {
            getCard(id).setCheck(check);       
        }

         //boolean tempGoal = true;
         
        public boolean isGoal(LinkedList<Boolean> goals)
        {
            return goals.size()==14;     
        }

        public void totalPairsCounter()
        {
            this.totalPairs++;
        }
        
        public void correctPairsCounter()
        {
            this.correctPairs++;
        }
        
        public int getTotalPairs()
        {
            return this.totalPairs + 1;
        }
        
        public int getCorrectPairs()
        {
            return this.correctPairs + 1;
        }
        
        private void setAccuracy()
        {
            this.accuracy = getCorrectPairs() * 100 / getTotalPairs();
            this.accuracy = Math.round (accuracy * 10.0) / 10.0;
        }
        
        public double getAccuracy()
        {
            setAccuracy();
            return this.accuracy;
        }
        
        public void scoreInc()
        {
            this.score = this.score + 5;
        }
        
        public void scoreDec()
        {
            this.score = this.score - 2;
        }
        
        public int getScore()
        {    
            return this.score +5;
        }
        
        int max = 0;
        public void maxScore(LinkedList<Integer> goals)
        {
            
            for(int i=0; i<goals.size(); i++)
            {
                if(goals.get(i) > max)
                    max = goals.get(i);
                    
            }
        }
        
        public int getMax()
        {
            return max;
        }
        
        public boolean newMaxScore()
        {
            return getScore() > max;
                
        }
        
}
