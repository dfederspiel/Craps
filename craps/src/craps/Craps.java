/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craps;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

// Main application game loop
public class Craps {

    public static void main(String[] args) {
        // Create the new game object
        Game game = new Game();

        // Add the playing pieces (can be any number and with n number of sides)
        game.addDie(new Die(4));
        game.addDie(new Die(4));
        game.addDie(new Die(4));
        while (!game.isOver) {
            game.placeBet();
            game.roll();
            game.playAgain();
        }
    }
}

// Represents a single die
class Die {

    int numSides;
    int sidesRolled[];
    int value;

    public Die(int sides) {
        this.numSides = sides;
        this.sidesRolled = new int[numSides];
    }

    public int roll() {
        int result = (int) (Math.random() * this.numSides);
        sidesRolled[result]++;
        this.value = result + 1;
        return this.value;
    }
}

// Game object
class Game {

    boolean isOver = false;
    int pot = 50;
    int valueMetrics[];

    private ArrayList<Die> dice = new ArrayList<>();

    int bet = 0;
    int calculateWins = 0;
    int losses = 0;
    int point = 0;
    int option = 0;

    int minRoll = 0;
    int maxRoll = 0;

  //int d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, d6 = 0;
    //int dt2 = 0, dt3 = 0, dt4 = 0, dt5 = 0, dt6 = 0, dt7 = 0, dt8 = 0, dt9 = 0, dt10 = 0, dt11 = 0, dt12 = 0;
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

  // Add die to game.  This is important as it will calculate minimum and maximum values, create
    // the valueMetrics variable which is an array, resized to the possible roll values, and then used to track
    // how many times a value has been hit.
    public void addDie(Die die) {
        this.dice.add(die);
        this.minRoll = dice.size();
        this.maxRoll = 0;
        for (int i = 0; i < dice.size(); i++) {
            this.maxRoll += dice.get(i).numSides;
        }
        valueMetrics = new int[(this.maxRoll - this.minRoll) + 1];
    }

    // Perform dice roll (aka, take a turn)
    public void roll() {

        rollDice();
        point = getTotal();
        valueMetrics[point - 1]++;

        if ((point == 7) || (point == 11)) {
            calculateWin();
        } else if ((point == 2) || (point == 3) || (point == 12)) {
            calculateLoss();
        } else {
            point();
        }
    }

    // Calculate the win scenario
    public void calculateWin() {
        calculateWins++;
        pot += bet;
        JOptionPane.showMessageDialog(null, "You rolled " + getTotal() + " YOU WIN! \n\nYour pot is $ " + pot);
    }

    // Calculate loss Scenario
    public void calculateLoss() {
        losses++;
        pot -= bet;
        JOptionPane.showMessageDialog(null, "You rolled " + getTotal() + " YOU LOSE! \n\nYour pot is $ " + pot);
    }

    // Calculate 'point' scenario
    public void point() {
        JOptionPane.showMessageDialog(null, "You rolled " + getTotal() + " (" + getIndividualValueString() + ") \n\nYou need " + point + " to calculateWin \n\nROLL AGAIN");
        rollDice();
        if (getTotal() == 7) {
            calculateLoss();
        } else if (getTotal() == point) {
            calculateWin();
        } else {
            point();
        }

    }

    // Play again dialog.
    public void playAgain() {
        option = JOptionPane.showConfirmDialog(null, "Do you want to play again?");
        if ((option == 1) || (option == 2)) {  //for NO and CANCEL options
            isOver = true;
            StringBuilder stats = new StringBuilder();
            stats.append("You WON " + calculateWins + " time(s) \n\nYou LOST " + losses + " time(s)");
            stats.append("\n\nYour total pot is $ " + pot + "\n\nStatistics Single Dice:\n");

            // First append all the individual dice and their individual stats, listing Die#, Sides, and counts for each.
            for (int i = 0; i < dice.size(); i++) {
                stats.append("Die #" + (i + 1) + "\n");
                for (int x = 0; x < dice.get(i).sidesRolled.length; x++) {
                    stats.append("S:" + (x + 1) + "-R" + dice.get(i).sidesRolled[x] + "\n");
                }
                stats.append("\n");
            }

            // Then look at each value in the value metrics and list the number of times a value was hit.
            for (int i = 0; i < valueMetrics.length; i++) {
                stats.append("V:" + (minRoll + i) + "-T:" + valueMetrics[i] + "\n");
            }

            JOptionPane.showMessageDialog(null, stats.toString());
        }
    }

    // Call the roll method of each die in the dice collection.
    public void rollDice() {
        for (int i = 0; i < dice.size(); i++) {
            dice.get(i).roll();
        }
    }

    // Gets a string with all rolled values
    public String getIndividualValueString() {
        String returnValue = "";
        for (int i = 0; i < dice.size(); i++) {
            returnValue += dice.get(i).value;
            if (i + 1 < dice.size()) {
                returnValue += ", ";
            }
        }
        return returnValue;
    }

    // Gets a grand total of each of the die in the dice collection.
    public int getTotal() {
        int total = 0;
        for (int i = 0; i < dice.size(); i++) {
            total += dice.get(i).value;
        }
        return total;
    }

    // Get total for a specific die by passing in an index.
    public int getTotal(int idx) {
        return dice.get(idx).value;
    }
}
