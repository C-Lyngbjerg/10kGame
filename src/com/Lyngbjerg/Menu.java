package com.Lyngbjerg;
import java.util.*;

public class Menu {
    public static void run(Scanner input, ArrayList<Player> playerList) {
        System.out.println("What kind of game would you like to play\nPress appropriate number for that option");
        System.out.println("1. Play regular game\n2. Playtest\n3. Exit");
        switch (input.nextInt()){
            case 1:
                playerReg(input, playerList);
                break;
            case 2:
                Play.game(input, playerList);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + input.nextInt());
        }
    }
    public static void playerReg(Scanner input, ArrayList<Player> playerList){
//        int numPlayers = 1;
        System.out.println("How many players are in this game?");
        int playerCount = input.nextInt();
        playerList.clear();
        for (int i = 0; i < playerCount; i++) {
            System.out.println("Name of player " + (i+1) + "?");
            String playerName = input.next();
            Player newPlayer = new Player(playerName);
            playerList.add(newPlayer);
        }
        System.out.println();
        int i = 1;
        for(Player p: playerList){
            System.out.println("Player "+i+": "+p.getPlayerName());
            i++;
        }
        Play.game(input, playerList);
    }
}
