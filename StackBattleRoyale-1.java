package com.king;

import java.util.Random;

public class StackBattleRoyale {
    private Player player1;
    private Player player2;
    private StackInterface<Disk> stackA;
    private StackInterface<Disk> stackB;
    private StackInterface<Disk> stackC;
    private Random rand;

    public StackBattleRoyale(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.stackA = new VectorStack<>();
        this.stackB = new LinkedStack<>();
        this.stackC = new ArrayStack<>();
        this.rand = new Random();
    }

    public void playGame(int totalRounds) {
        for (int round = 1; round <= totalRounds; round++) {
            System.out.println("Round " + round + ":");
            turn(player1);
            turn(player2);
            popSchedule(round);
            System.out.println();
        }
        endGame();
    }

    public void turn(Player player) {
        int stackChoice = rand.nextInt(3);
        StackInterface<Disk> cStack = null;
        String stackName = "";

        if (stackChoice == 0) {
            cStack = stackA;
            stackName = "A";
        } else if (stackChoice == 1) {
            cStack = stackB;
            stackName = "B";
        } else if (stackChoice == 2) {
            cStack = stackC;
            stackName = "C";
        }

        System.out.print(player.getName() + " pushes onto Stack " + stackName);

        if (!cStack.isEmpty()) {
            Disk top = cStack.peek();
            if(!top.getOwner().equals(player)) {
                cStack.pop();
                System.out.println(" and pops " + top.getOwner().getName() + "'s disk.");
            } else {
                System.out.println(".");
            }
        } else {
            System.out.println(".");
        }

        cStack.push(new Disk(player));
    }

    private void popSchedule(int round) { 
        if (round % 3 == 0) {
            popFrom(stackA, "A");
        }
        if (round % 3 == 0) {
            popFrom(stackB, "B");
        }
        if (round % 3 == 0) {
            popFrom(stackC, "C");
        }
    }
    
    private void popFrom(StackInterface<Disk> stack, String name) {
        if (!stack.isEmpty()) {
            Disk removed = stack.pop();
            System.out.printf("%s's disk was removed from Stack %s due to pop schedule.\n", removed.getOwner().getName(), name);
        } else {
            System.out.println("No disk removed due to empty stack.");
        }
    }

    private void endGame() {
        int player1Count = cDisks(stackA, player1) + cDisks(stackB, player1) + cDisks(stackC, player1);
        int player2Count = cDisks(stackA, player2) + cDisks(stackB, player2) + cDisks(stackC, player2);

        System.out.println("Game over!");
        System.out.println(player1.getName() + " has " + player1Count + " disks remaining.");
        System.out.println(player2.getName() + " has " + player2Count + " disks remaining.");

        if (player1Count > player2Count) {
            System.out.println(player1.getName() + " wins!");
        } else if (player2Count > player1Count) {
            System.out.println(player2.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private int cDisks(StackInterface<Disk> stack, Player player) {
        int count = 0;
        while (!stack.isEmpty()) {
            Disk d = stack.pop();
            if (d.getOwner().equals(player))
                count++;
        }
        return count;
    }
}
