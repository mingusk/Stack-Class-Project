package com.king;

public class Disk {
    private Player owner;

    public Disk(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public String toString() {
        return owner.getName() + "'s disk";
    }
}
