package com.Lyngbjerg;

import java.util.ArrayList;
import java.util.Scanner;

public class Check {
    public static int diceCheck(Scanner input, Player currentPlayer, ArrayList<Integer> diceRolled, int tempDiceKeep){
        int diceCount = 0;
        for (int i: diceRolled) {
            if(i == 1 || i == 5){
                diceCount++;
            }
        }
        if(diceCount > 0) {
            return Score.printPrompt(input, currentPlayer);
        }else{
            diceCount = 0;
            for (int i :diceRolled) {
                for(int j = 0; j < diceRolled.size(); j++){
                    if(diceRolled.get(j) == i){
                        diceCount++;
                    }
                }
                diceCount--;
            }
/*
            if int diceCount does not reach >4 there are no triples or better and since previous if statements checks
            for 1s and 5s there are no eligible dice and the player's turn is ended.
*/
            if(!(diceCount > 4)){
                System.out.println("\nNo eligible dice, your turn has ended and your temporary score has been reset.");
                currentPlayer.setTempScore(0);
                return -1;
            }
            return Score.printPrompt(input, currentPlayer);
        }
    }
}
