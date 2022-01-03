package com.nunolima.gods;

import com.nunolima.game.Player;
import static com.nunolima.utils.Utilities.*;

public class Brunhild extends Sacred {

    public Brunhild() {

        this.priority = 3;
        this.selected = false;
        this.name = "BRUNHILD";
        this.summedUpInfo = "\u001b[4m3- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nMultiply rolled Axes by 1.5x, 2x or 3x.";
        this.favourLevel1 = new String[]{"1", "6", "Multiply Axe Dice by 1.5x (rounded)"};
        this.favourLevel2 = new String[]{"2", "10", "Multiply Axe Dice by 2x"};
        this.favourLevel3 = new String[]{"3", "18", "Multiply Axe Dice by 3x"};

    }


    @Override
    public void action(Player... players) {
        int tokens;
        switch (this.favourPower) {
            case 1:
                tokens = 6;
                break;
            case 2:
                tokens = 10;
                break;
            case 3:
                tokens = 18;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + this.favourPower);
        }
        if (players[0].getFavourTokens() - tokens < 0) {
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Brunhild for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens),0));
        System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 6:
                System.out.println("Through " + YELLOW + "Brunhild's Fury " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " multiplies the Axe dice by 1.5x.");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (Math.round(players[0].getAxeCounter() * 1.5) - players[0].getAxeCounter()) + " extra Axes!");
                players[0].setAxeCounter((int) Math.round(players[0].getAxeCounter() * 1.5));
                pressEnterToContinue();
                break;
            case 10:
                System.out.println("Through " + YELLOW + "Brunhild's Fury " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " multiplies the Axe dice by 2x.");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + ((players[0].getAxeCounter() * 2) - players[0].getAxeCounter()) + " extra Axes!");
                players[0].setAxeCounter(players[0].getAxeCounter() * 2);
                pressEnterToContinue();
                break;
            case 18:
                System.out.println("Through " + YELLOW + "Brunhild's Fury " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " multiplies the Axe dice by 3x.");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + ((players[0].getAxeCounter() * 3) - players[0].getAxeCounter()) + " extra Axes!");
                players[0].setAxeCounter(players[0].getAxeCounter() * 3);
                pressEnterToContinue();
                break;
        }
    }

}

