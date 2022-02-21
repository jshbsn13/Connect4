package com.company;

public class Player {
    private String name;
    private char color;
    private Player nextPlayer;

    public Player(String name, char color){
        this.name = name;
        this.color = color;

    }

    public void setNextPlayer(Player next){
        this.nextPlayer = next;
    }

    public String getName() {
        return name;
    }

    public Player getNextPlayer(){
        return nextPlayer;
    }

    public char getColor() {
        return color;
    }
}


