package com.company;

import java.io.Console;

public class GameBoard {
    int rowSize = 6;
    int columnSize = 7;

    //found out how to do colors from internet
    public static final String RED = "\033[0;91m";       // RED
    public static final String YELLOW = "\033[0;93m";    // BRIGHT YELLOW
    public static final String ANSI_RESET = "\u001B[0m"; //this resets color

    private char[][] gameBoard = new char[rowSize][columnSize];

    public boolean addPieceToColumn(int col, Player player) {


        for (int row = rowSize - 1; row >= 0; row--) {
            if (gameBoard[row][col] == 0) {
                gameBoard[row][col] = player.getColor();
                return true;
            }
        }

        return false;
    }

    public void displayGameBoard() {
        char div = '|';

        System.out.println("******************************************");
        System.out.println("       Connect Four");
        System.out.println("  0   1   2   3   4   5   6");
        System.out.println("----------------------------");

        //prints one row at a time
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < columnSize; col++) {

                if (gameBoard[row][col] == 'R') {
                    System.out.print(div + " " + RED + gameBoard[row][col] + " " + ANSI_RESET); //prints red if player 1


                } else if (gameBoard[row][col] == 'Y') {
                    System.out.print(div + " " + YELLOW + gameBoard[row][col] + " " + ANSI_RESET); // prints yellow if player 2

                } else {
                    System.out.print(div + " " + gameBoard[row][col] + " ");

                }

            }
            System.out.println();
        }
        System.out.println("******************************************");
        System.out.println();


    }

    public boolean fourInARow(Player currentPlayer) {

        char playerPiece = currentPlayer.getColor();
        int totalAdjacent = 0;

        //check for four horizontal
        for (int row = rowSize - 1; row >= 0; row--) {
            for (int col = 0; col < columnSize; col++) {
                if (gameBoard[row][col] != playerPiece) { //it is not the players color
                    totalAdjacent = 0;

                } else {
                    //it is players color
                    totalAdjacent++;

                    if (totalAdjacent == 4) {
                        return true;
                    }
                }
            }
        }


        //check for four vertical
        totalAdjacent = 0;
        for (int col = 0; col < columnSize; col++) {
            for (int row = rowSize - 1; row >= 0; row--) {
                if (gameBoard[row][col] == playerPiece) {
                    totalAdjacent++;

                    if (totalAdjacent == 4) {
                        return true;
                    }
                } else {
                    totalAdjacent = 0;
                }
            }
        }
        //check for four diagonally
        totalAdjacent = 0;
        int startingRow = 0;
        int startingCol = 0;
        for (int row = rowSize - 1; row >= 0; row--) {
            for (int col = 0; col < columnSize; col++) {
                if (gameBoard[row][col] == playerPiece) {

                    //when a match is found this is the element location in the array
                    startingRow = row;
                    startingCol = col;


                    //make sure the 4 length diagonal will fit in array up and to left
                    if (startingRow - 3 >= 0 && startingCol - 3 >= 0) {
                        totalAdjacent = 1;
                        //check up and to left
                        for (int i = 1; i < 4; i++) {
                            if (gameBoard[row - i][col - i] == playerPiece) {
                                totalAdjacent++;
                                if (totalAdjacent == 4) {
                                    return true;
                                }
                            } else {
                                totalAdjacent = 1;
                            }
                        }
                    }

                    totalAdjacent = 1;
                    //make sure the 4 length diagonal will fit in array up and to right
                    if (startingRow - 3 >= 0 && startingCol+3 < columnSize) {
                        //check up and to right
                        for (int i = 1; i < 4; i++) {
                            if (gameBoard[row - i][col + i] == playerPiece) {
                                totalAdjacent++;
                                if (totalAdjacent == 4) {
                                    return true;
                                }
                            } else {


                                totalAdjacent = 1;
                            }
                        }
                    }

                }
            }
        }
        return false;


    }

}
