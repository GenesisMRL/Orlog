package com.nunolima.game;

import com.nunolima.gods.Sacred;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.nunolima.game.Game.menuSection;
import static com.nunolima.game.Leaderboard.addPlayerToLeaderboard;
import static com.nunolima.utils.Utilities.*;

public class Player {

    private String name;
    private final String playerColour;
    private int health;
    private int favourTokens;
    private List<Sacred> gods = new ArrayList<>();
    private List<Die> diceSet;
    private final List<String> selectedDiceFaces = new ArrayList<>();
    private int axeHitCounter;
    private int axeBlockCounter;
    private int arrowHitCounter;
    private int arrowBlockCounter;
    private int arrowCounter;
    private int axeCounter;
    private int shieldCounter;
    private int helmetCounter;
    private int handCounter;

    public Player(String playerColour) {

        this.diceSet = new ArrayList<>(Arrays.asList(
                /*Die1*/new Die("Axe", "Shield", "Arrow + \u001B[33mToken\u001B[0m", "Axe", "Helmet", "Hand + \u001B[33mToken\u001B[0m"),
                /*Die2*/new Die("Axe", "Shield + \u001B[33mToken\u001B[0m", "Arrow", "Axe", "Helmet", "Hand + \u001B[33mToken\u001B[0m"),
                /*Die3*/new Die("Axe", "Shield", "Arrow + \u001B[33mToken\u001B[0m", "Axe", "Helmet + \u001B[33mToken\u001B[0m", "Hand"),
                /*Die4*/new Die("Axe", "Shield", "Arrow", "Axe", "Helmet + \u001B[33mToken\u001B[0m", "Hand + \u001B[33mToken\u001B[0m"),
                /*Die5*/new Die("Axe", "Shield + \u001B[33mToken\u001B[0m", "Arrow + \u001B[33mToken\u001B[0m", "Axe", "Helmet", "Hand"),
                /*Die6*/new Die("Axe", "Shield + \u001B[33mToken\u001B[0m", "Arrow", "Axe", "Helmet + \u001B[33mToken\u001B[0m", "Hand")));
        this.name = "";
        this.playerColour = playerColour;

        this.health = 15;
        this.favourTokens = 0;

        this.axeCounter = 0;
        this.arrowCounter = 0;
        this.shieldCounter = 0;
        this.helmetCounter = 0;
        this.handCounter = 0;

        this.axeHitCounter = 0;
        this.axeBlockCounter = 0;
        this.arrowHitCounter = 0;
        this.arrowBlockCounter = 0;
    }

    /**
     * PRINTS
     **/

    public void printSimplePlayerHPAndTokens() {
        System.out.printf("%-15s %30s %n", this.playerColour + this.name + RESET + ": ", this.health + " HP | " + this.favourTokens + " Tokens");
    }

    public void printPlayerHPAndTokensTable() {
        String heading1 = "HEALTH";
        String heading2 = "TOKENS";
        String divider = "------------------------------------------------------------";
        System.out.println(WHITE_BACKGROUND + BLACK);
        System.out.println(this.name);
        System.out.println(divider);
        System.out.printf("%15s %30s %n", heading1, heading2);
        System.out.println(divider);
        System.out.printf("%15s %30s %n", this.health, this.favourTokens);
        System.out.println(divider + "\n" + RESET);
    }

    public void printGodsSummedUp() {
        System.out.println(CLEAR + this.playerColour + this.name + "'s Gods:\n\n" + RESET);
        for (Sacred god : this.gods)
            god.printInfo();
    }

    public void printGodsFavours() {
        System.out.println(CLEAR + this.playerColour + this.name + "'s Gods:\n" + RESET);
        for (int i = 0; i < this.gods.size(); i++) {
            System.out.println(YELLOW_BACKGROUND + BLACK);
            System.out.print("God " + (i + 1));
            gods.get(i).printFavour();
            System.out.println(RESET);
        }
    }

    public void printSelectedDiceFaces() {
        System.out.println("\n-----------------------------------------------");
        System.out.println(playerColour + name + RESET + "'s Dice:");
        for (String dieFace : selectedDiceFaces)
            System.out.println(dieFace);

    }

    /**
     * TOOLS
     **/

    public void addDieFaceToSelection(String dieFace) {
        this.selectedDiceFaces.add(dieFace);
    }

    public void updateTokens() {
        for (String dieFace : this.selectedDiceFaces) {
            if (dieFace.matches(".*Token.*"))
                this.favourTokens++;
        }
    }

    public void updateCounters() {
        for (String dieFace : this.selectedDiceFaces) {
            if (dieFace.matches(".*Axe.*"))
                this.axeCounter++;
            if (dieFace.matches(".*Arrow.*"))
                this.arrowCounter++;
            if (dieFace.matches(".*Shield.*"))
                this.shieldCounter++;
            if (dieFace.matches(".*Helmet.*"))
                helmetCounter++;
            if (dieFace.matches(".*Hand.*"))
                this.handCounter++;
        }
    }

    public void updateHitsAndBlocks(Player opposingPlayer) {

        if (this.axeCounter >= opposingPlayer.helmetCounter) {
            this.axeHitCounter = this.axeCounter - opposingPlayer.helmetCounter;
            opposingPlayer.setAxeBlockCounter(opposingPlayer.helmetCounter);
        } else {
            opposingPlayer.setAxeBlockCounter(this.axeCounter);
        }

        if (this.arrowCounter >= opposingPlayer.shieldCounter) {
            this.arrowHitCounter = this.arrowCounter - opposingPlayer.shieldCounter;
            opposingPlayer.setArrowBlockCounter(opposingPlayer.shieldCounter);
        } else {
            opposingPlayer.setArrowBlockCounter(this.arrowCounter);
        }

        if (opposingPlayer.axeCounter >= this.helmetCounter) {
            opposingPlayer.setAxeHitCounter(opposingPlayer.axeCounter - this.helmetCounter);
            this.axeBlockCounter = opposingPlayer.helmetCounter;
        } else {
            this.axeBlockCounter = opposingPlayer.axeCounter;
        }

        if (opposingPlayer.arrowCounter >= this.shieldCounter) {
            opposingPlayer.setArrowHitCounter(opposingPlayer.arrowCounter - this.shieldCounter);
            this.arrowBlockCounter = this.shieldCounter;
        } else {
            this.arrowBlockCounter = opposingPlayer.arrowCounter;
        }
    }

    public void resetCounters() {
        this.axeCounter = 0;
        this.axeBlockCounter = 0;
        this.axeHitCounter = 0;
        this.arrowCounter = 0;
        this.arrowBlockCounter = 0;
        this.arrowHitCounter = 0;
        this.shieldCounter = 0;
        this.helmetCounter = 0;
        this.handCounter = 0;
    }

    public void resetDiceSet() {
        this.diceSet.clear();
        this.diceSet = new ArrayList<>(Arrays.asList(
                new Die("Axe", "Shield", "Arrow + \u001B[33mToken\u001B[0m", "Axe", "Helmet", "Hand + \u001B[33mToken\u001B[0m"),
                new Die("Axe", "Shield + \u001B[33mToken\u001B[0m", "Arrow", "Axe", "Helmet", "Hand + \u001B[33mToken\u001B[0m"),
                new Die("Axe", "Shield", "Arrow + \u001B[33mToken\u001B[0m", "Axe", "Helmet + \u001B[33mToken\u001B[0m", "Hand"),
                new Die("Axe", "Shield", "Arrow", "Axe", "Helmet + \u001B[33mToken\u001B[0m", "Hand + \u001B[33mToken\u001B[0m"),
                new Die("Axe", "Shield + \u001B[33mToken\u001B[0m", "Arrow + \u001B[33mToken\u001B[0m", "Axe", "Helmet", "Hand"),
                new Die("Axe", "Shield + \u001B[33mToken\u001B[0m", "Arrow", "Axe", "Helmet + \u001B[33mToken\u001B[0m", "Hand")));
    }

    public void resetSelectedDiceFaces() {
        this.selectedDiceFaces.clear();
    }

    public void checkIfLostAgainst(Player opposingPlayer) {
        if (health <= 0) {
            System.out.println(CLEAR + playerColour + name + " tragically died!\n");
            System.out.println(opposingPlayer.getPlayerColour() + opposingPlayer.getName() + RESET + " wins! Congratulations!");
            addPlayerToLeaderboard(opposingPlayer.name);

            pressEnterToContinue();
            menuSection();
        }
    }

    /**
     * GETTERS
     **/

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getFavourTokens() {
        return favourTokens;
    }

    public List<Sacred> getGods() {
        return gods;
    }

    public List<Die> getDiceSet() {
        return diceSet;
    }

    public List<String> getSelectedDiceFaces() {
        return selectedDiceFaces;
    }

    public String getPlayerColour() {
        return playerColour;
    }

    public int getArrowHitCounter() {
        return arrowHitCounter;
    }

    public int getAxeHitCounter() {
        return axeHitCounter;
    }

    public int getArrowBlockCounter() {
        return arrowBlockCounter;
    }

    public int getAxeBlockCounter() {
        return axeBlockCounter;
    }

    public int getArrowCounter() {
        return arrowCounter;
    }

    public int getAxeCounter() {
        return axeCounter;
    }

    public int getHelmetCounter() {
        return helmetCounter;
    }

    public int getShieldCounter() {
        return shieldCounter;
    }

    public int getHandCounter() {
        return handCounter;
    }

    /**
     * SETTERS
     **/

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setFavourTokens(int favourTokens) {
        this.favourTokens = favourTokens;
    }

    public void setGods(List<Sacred> gods) {
        this.gods = gods;
    }

    public void setArrowCounter(int arrowCounter) {
        this.arrowCounter = arrowCounter;
    }

    public void setAxeCounter(int axeCounter) {
        this.axeCounter = axeCounter;
    }

    public void setHelmetCounter(int helmetCounter) {
        this.helmetCounter = helmetCounter;
    }

    public void setShieldCounter(int shieldCounter) {
        this.shieldCounter = shieldCounter;
    }

    public void setHandCounter(int handCounter) {
        this.handCounter = handCounter;
    }

    public void setArrowBlockCounter(int arrowBlockCounter) {
        this.arrowBlockCounter = arrowBlockCounter;
    }

    public void setAxeBlockCounter(int axeBlockCounter) {
        this.axeBlockCounter = axeBlockCounter;
    }

    public void setArrowHitCounter(int arrowHitCounter) {
        this.arrowHitCounter = arrowHitCounter;
    }

    public void setAxeHitCounter(int axeHitCounter) {
        this.axeHitCounter = axeHitCounter;
    }

}