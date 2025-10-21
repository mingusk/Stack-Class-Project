package com.king;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of rounds to play (n ≥ 30): ");
        int rounds = scan.nextInt();

        if (rounds < 30) {
            System.out.println("You must play at least 30 rounds!");
            return;
        }

        Player Sergio = new Player("Sergio");
        Player Adelphi = new Player("Adelphi");

        StackBattleRoyale game = new StackBattleRoyale(Sergio, Adelphi);
        game.playGame(rounds);
    }
}
