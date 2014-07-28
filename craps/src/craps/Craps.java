/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craps;

import javax.swing.JOptionPane;

/**
 *
 * @author dfederspiel
 */
public class Craps {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game game = new Game();
        while (!game.isOver) {
            game.placeBet();
            game.roll();
            game.playAgain();
        }
    }
}

class Game {

    boolean isOver = false;
    int pot = 50;
    int diceOne = 0;
    int diceTwo = 0;
    int bet = 0;
    int wins = 0;
    int loss = 0;
    int point = 0;
    int option = 0;
    int d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, d6 = 0;
    int dt2 = 0, dt3 = 0, dt4 = 0, dt5 = 0, dt6 = 0, dt7 = 0, dt8 = 0, dt9 = 0, dt10 = 0, dt11 = 0, dt12 = 0;

    public void placeBet() {
        String betString = JOptionPane.showInputDialog(null, "LET'S PLAY CRAPS!\n\n Your pot is $ " + pot
                + "\n\nHow much do you want to bet?", "Enter a bet", JOptionPane.QUESTION_MESSAGE);

        try {
            bet = Integer.parseInt(betString);  //convert string to an integer
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "you suck try again");
            placeBet();
        }

        if (bet > pot) {
            JOptionPane.showInputDialog(null, "Your bet is too high, \nYour pot is $ " + pot + "\nEnter a valid bet", "Enter a valid bet", JOptionPane.QUESTION_MESSAGE);
            placeBet();
        }

    }

    public void roll() {

        rollDice();

        point = diceOne + diceTwo;

        if ((diceOne + diceTwo == 7) || (diceOne + diceTwo == 11)) {
            win();
        } else if ((diceOne + diceTwo == 2) || (diceOne + diceTwo == 3) || (diceOne + diceTwo == 12)) {
            lose();
        } else {
            point();
        }
    }

    public void win() {
        wins++;
        pot += bet;
        JOptionPane.showMessageDialog(null, "You rolled " + (diceOne + diceTwo) + " YOU WIN! \n\nYour pot is $ " + pot);

    }

    public void lose() {
        loss++;
        pot -= bet;
        JOptionPane.showMessageDialog(null, "You rolled " + (diceOne + diceTwo) + " YOU LOSE! \n\nYour pot is $ " + pot);

    }

    public void point() {
        JOptionPane.showMessageDialog(null, "You rolled " + (diceOne + diceTwo) + " (" + diceOne
                + " and " + diceTwo + ") \n\nYou need " + point + " to win \n\nROLL AGAIN");
        rollDice();
        if (diceOne + diceTwo == 7) {
            lose();
        } else if (diceOne + diceTwo == point) {
            win();
        } else {
            point();
        }

    }

    public void playAgain() {
        option = JOptionPane.showConfirmDialog(null, "Do you want to play again?");
        if ((option == 1) || (option == 2)) {  //for NO and CANCEL options
            isOver = true;
            JOptionPane.showMessageDialog(null, "You WON " + wins + " time(s) \n\nYou LOST " + loss + " time(s)"
                    + "\n\nYour total pot is $ " + pot + "\n\nStatistics Single Dice:\nOnes: " + d1
                    + "\nTwos: " + d2 + "\nThrees: " + d3 + "\nFours: " + d4 + "\nFives: " + d5
                    + "\nSixes: " + d6 + "\n\nStatistics Dice Total:\nTwo: " + dt2 + "\nThree: " + dt3
                    + "\nFour: " + dt4 + "\nFive: " + dt5 + "\nSix: " + dt6 + "\nSeven: " + dt7
                    + "\nEight: " + dt8 + "\nNine: " + dt9 + "\nTen: " + dt10 + "\nEleven: " + dt11
                    + "\nTwelve: " + dt12 + "\n\nThank you for playing!");
        }
    }

    public void rollDice() {
        diceOne = (int) (Math.random() * 6 + 1);
        diceTwo = (int) (Math.random() * 6 + 1);
        stats();

    }

    public void stats() {
        if (diceOne == 1) {
            d1++;
        }
        if (diceOne == 2) {
            d2++;
        }
        if (diceOne == 3) {
            d3++;
        }
        if (diceOne == 4) {
            d4++;
        }
        if (diceOne == 5) {
            d5++;
        }
        if (diceOne == 6) {
            d6++;
        }
        if (diceTwo == 1) {
            d1++;
        }
        if (diceTwo == 2) {
            d2++;
        }
        if (diceTwo == 3) {
            d3++;
        }
        if (diceTwo == 4) {
            d4++;
        }
        if (diceTwo == 5) {
            d5++;
        }
        if (diceTwo == 6) {
            d6++;
        }
        if (diceOne + diceTwo == 2) {
            dt2++;
        }
        if (diceOne + diceTwo == 3) {
            dt3++;
        }
        if (diceOne + diceTwo == 4) {
            dt4++;
        }
        if (diceOne + diceTwo == 5) {
            dt5++;
        }
        if (diceOne + diceTwo == 6) {
            dt6++;
        }
        if (diceOne + diceTwo == 7) {
            dt7++;
        }
        if (diceOne + diceTwo == 8) {
            dt8++;
        }
        if (diceOne + diceTwo == 9) {
            dt9++;
        }
        if (diceOne + diceTwo == 10) {
            dt10++;
        }
        if (diceOne + diceTwo == 11) {
            dt11++;
        }
        if (diceOne + diceTwo == 12) {
            dt12++;
        }

    }
}
