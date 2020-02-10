package com.Lyngbjerg;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Player> playerList = new ArrayList<>();
        Player test = new Player("ChrisTest");
        playerList.add(test);
        Scanner input = new Scanner(System.in);
        Menu.run(input, playerList);
    }
}
