package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        char play = 'y';

        Scanner sc = new Scanner(System.in);

        //play or replay the game loop
        do {
            GameBoard gameBoard = new GameBoard();                   //create new gameboard
            Player player1 = new Player("Player 1", 'R'); //create player 1 with red pieces
            Player player2 = new Player("Player 2", 'Y'); //create player 2 with yellow pieces

            player1.setNextPlayer(player2); //sets who is after this player
            player2.setNextPlayer(player1);

            Player currentPlayer = player1;                            //keeps track of whose turn it is

            boolean gameWon = false;


            //play connect four until someone has won
            while (!gameWon) {

                gameBoard.displayGameBoard();



                int column = -1;
                do {
                    System.out.println("It is " + currentPlayer.getName() + " turn. Please choose column to put your piece: ");
                    try {
                        column = sc.nextInt(); //get user input
                    } catch (Exception e) {
                        //user entered something other than an int

                        System.out.println("Must be an integer!");
                        column = -1; //just set column to an invalid int to restart loop
                        sc.next(); //without this it was never ending looping
                    }

                    if(!isValidColumn(column)){
                        System.out.println("Please enter column 0-6!");
                    }

                } while (!isValidColumn(column));

                //attempt to add piece to board if column is full display error
                if(gameBoard.addPieceToColumn(column, currentPlayer)){

                    //check for a winner
                    if(gameBoard.fourInARow(currentPlayer)){
                        gameBoard.displayGameBoard();
                        displayWinner(currentPlayer);
                        gameWon = true;
                    }



                    //change current player to the next player
                    currentPlayer = currentPlayer.getNextPlayer();
                }else {
                    System.out.println("Pick a different row!"); //if column is full
                }




            }

            //ask the player if they want to play again, loop until they provide valid input
            do {
                System.out.println("Would you like to play again? y or n: ");
                try {
                    play = sc.next().charAt(0);
                } catch (Exception e) {
                    sc.next();
                    play = 'z';
                }

                if(!isYesOrNo(play)){
                    System.out.println("Please enter y or n!");
                }
            } while (!isYesOrNo(play));

        }while (play == 'y' || play == 'Y');
    }

    private static boolean isYesOrNo(char play) {

        return (play == 'y' || play == 'Y' || play == 'n' || play == 'N');
    }

    private static void displayWinner(Player currentPlayer) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(currentPlayer.getName() + " has won the game!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }



    public static boolean isValidColumn(int col){
        return (col >= 0 && col <= 6);
    }
}
