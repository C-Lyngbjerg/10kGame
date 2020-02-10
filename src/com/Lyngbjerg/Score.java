package com.Lyngbjerg;
import java.util.*;

public class Score {
    public static void keepScore(Player currentPlayer, ArrayList<Integer> diceRolled, int diceAdd, Scanner input, int tempDiceKeep, ArrayList<Integer> diceKept) {
        switch(tempDiceKeep) {
            case 1:
                addScoreOneOrFive(currentPlayer, input,diceRolled, diceKept, diceAdd);
                break;
            case 2:
                for (int i =0; i <tempDiceKeep;i++) {
                    addScoreOneOrFive(currentPlayer, input,diceRolled, diceKept, diceAdd);
                }
                break;
            default:
                int idenDiceNr;
                int[] idenDiceValue = {1, 1, 1, 1, 2, 4, 8};
                if(tempDiceKeep==-1) {
                    break;
                }
                if(tempDiceKeep==3){
                    System.out.println("Press 1: for a triple\nPress 2: for a combination of 1s and 5s");
                }else {
                    System.out.println("Press 1: for a triple or better only \nPress 2: triple or better + extra 1s or 5s");
                    if (tempDiceKeep == 6) {
                        System.out.println("Press 3: 1-6, 2 triples or 3 pairs");
                    }
                }
                int tripleOrBetter = input.nextInt();
                if(tripleOrBetter == 1){
                    idenDiceNr = tempDiceKeep;
                    currentPlayer.setTempScore(currentPlayer.getTempScore() + (addScoreTriple(idenDiceNr, input, diceRolled, diceAdd))*idenDiceValue[idenDiceNr]);
                }else {
                    if (tempDiceKeep == 6) {
                        System.out.println("Press 1 for 1-6\nPress 2 for 2 triples\nPress 3 for 3 pairs");
                        switch (input.nextInt()){
                            case 1:
                                currentPlayer.setTempScore(currentPlayer.getTempScore()+1000);
                                break;
                            case 2:
                                System.out.println("First choose you first triple and then your second");
                                for (int i = 0; i < 2 ; i++) {
                                    currentPlayer.setTempScore(currentPlayer.getTempScore() + (addScoreTriple(3, input, diceRolled, diceAdd)));
                                }
                                break;
                            default:
                                currentPlayer.setTempScore(currentPlayer.getTempScore()+1500);
                                break;
                        }
                    } else{
                        if(tempDiceKeep==3 && tripleOrBetter == 2 ){
                            for (int i = 0; i < tempDiceKeep; i++) {
                                addScoreOneOrFive(currentPlayer, input, diceRolled, diceKept, diceAdd);
                            }
                        }else {
                            System.out.println("How many identical dice do you have?");
                            idenDiceNr = input.nextInt();
                            System.out.println("First choose your identical dice");
                            currentPlayer.setTempScore(currentPlayer.getTempScore() + (addScoreTriple(idenDiceNr, input, diceRolled, diceAdd)) * idenDiceValue[idenDiceNr]);
                            System.out.println("Now choose your individual dice");
                            for (int i = 0; i < tempDiceKeep - idenDiceNr; i++) {
                                addScoreOneOrFive(currentPlayer, input, diceRolled, diceKept, diceAdd);
                            }
                        }
                    }
                }
                break;
        }
    }

    public static int addScoreTriple(int idenDiceNr,Scanner input,ArrayList<Integer> diceRolled, int diceAdd){
        for(int j = 0; j < idenDiceNr; j++) {
            System.out.println("Which dice to keep?");
            diceAdd += diceRolled.get((input.nextInt() - 1));
        }
        diceAdd /=idenDiceNr;
        if(diceAdd == 1){
            return (diceAdd*1000);
        }else{
            return (diceAdd*100);
        }

    }

    //If player adds a 'single' dice (1/5) to score - either 100 or 50 points respectively
    public static void addScoreOneOrFive(Player currentPlayer,Scanner input,ArrayList<Integer> dice, ArrayList<Integer> diceKept, int diceAdd){
        System.out.println("Which dice to keep?");
        diceAdd = dice.get((input.nextInt()-1));
        switch(diceAdd){
            case 1:
                currentPlayer.setTempScore(currentPlayer.getTempScore()+((diceAdd)*100));
                break;
            default:
                currentPlayer.setTempScore(currentPlayer.getTempScore()+((diceAdd)*10));
                break;
        }
    }

    public static void printScore(ArrayList<Player> playerList){
        System.out.println("\n_____________");
        for(Player p: playerList){
            System.out.printf("%-6s: %5d\n",p.getPlayerName(),p.getScore());
        }
        System.out.println("-------------\n");
    }

    public static int printPrompt(Scanner input, Player currentPlayer){
        System.out.println("  | Current Score: " + currentPlayer.getTempScore());
        System.out.println("\nHow many dice to keep?");
        int response = input.nextInt();
        if(response == -1){
            currentPlayer.setScore(currentPlayer.getTempScore()+currentPlayer.getScore());
            return -1;
        }else{
            return response;
        }
    }
}
