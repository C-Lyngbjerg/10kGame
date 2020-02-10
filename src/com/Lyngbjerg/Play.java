package com.Lyngbjerg;
import java.util.*;

public class Play {
    public static void game(Scanner input, ArrayList<Player> playerList){
        Random rand = new Random();
        ArrayList<Integer> diceRolled = new ArrayList<>();
        ArrayList<Integer> diceKept  = new ArrayList<>();
        int[] diceLeft = {0, 1, 2, 3, 4, 5, 6};
        int round = 1;
        for(int player = 0; player < playerList.size(); player++){
            Score.printScore(playerList);
            System.out.println("It is currently "+playerList.get(player).getPlayerName()+"'s turn:");
            rollDice(playerList, player, diceRolled, diceKept, diceLeft, rand, input);
//            ikke sikker på hvad det her gør, men har ikke lyst til at slette det endnu
//            for(int j = 0; j<diceKept.size()-1; j++) {
//                System.out.println("d"+(j+1)+" = "+diceKept.get(j)+" ");
//            }

//            Rounds do not have a gameplay impact other than to tell the program to start the rotation of
//            players over from the start. Resets by setting int player to -1 which is then set to 0 when loop iterates.
            if(player == (playerList.size()-1)) {
                System.out.println("End of round "+round);
//                Score.printScore(playerList);
                round++;
                player = -1;
            }
        }
    }
//  Player round which runs while flag and has tests for the playturn to end which each returns false
    public static boolean rollDice(ArrayList<Player> playerList,int playerNr, ArrayList<Integer> diceRolled, ArrayList<Integer> diceKept,int[] diceLeft, Random r, Scanner input) {
        Player currentPlayer = playerList.get(playerNr);
        int diceKeepNr = 0;
        int tempDiceKeep = 0;
        int diceAdd = 0;
        boolean flagTwo = true;
        do {
//          Checks with current player if they want to end their turn or continue rolling - if n then adds current
//          player's temporary score to their permanent Score field and ends their turn.
            if((!(diceKeepNr==0)) || (currentPlayer.getTempScore()<350 && !(diceKeepNr==0))){
                System.out.println("\nDo you want to roll again? Y/N");
                if(!input.next().equalsIgnoreCase("y")){
                    currentPlayer.setScore(currentPlayer.getScore()+currentPlayer.getTempScore());
                    currentPlayer.setTempScore(0);
                    break;
                }
            }
//          Rolls and prints out dice values - number of dice according to dice left (6-diceKeepNr).
            System.out.println("");
            for(int i=0; i < diceLeft[(6-diceKeepNr)]; i++) {
                diceRolled.add(r.nextInt(6)+1);
                System.out.print(diceRolled.get(i)+" ");
            }

//          checks dice for 1s and 5s which are eligible to score - else ends sequence
            tempDiceKeep = Check.diceCheck(input, currentPlayer, diceRolled, tempDiceKeep);
            if(tempDiceKeep == -1){
                flagTwo = false;
            }

            diceKeepNr += tempDiceKeep;
            Score.keepScore(currentPlayer, diceRolled, diceAdd, input, tempDiceKeep, diceKept);
            diceRolled.clear();

//            Checks whether all dice have been succesfully used and resets 'number dice left' (diceKeepNr) if true
            if(diceKeepNr == 6) {
                System.out.print("You have used all 6 dice, you may now begin again with a full set of dice");
                diceKeepNr = 0;
            }
        } while(flagTwo);
        return false;
    }
}
