package com.nunolima.gods;

import com.nunolima.game.Player;
import static com.nunolima.utils.Utilities.*;

public class Ullr extends Sacred {

    public Ullr() {

        this.priority = 3;
        this.selected = false;
        this.name = "ULLR";
        this.summedUpInfo = "\u001b[4m13- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nIgnore 2, 3 or 6 enemy Shields.";
        this.favourLevel1 = new String[]{"1", "2", "Ignore 2 enemy Shields"};
        this.favourLevel2 = new String[]{"2", "3", "Ignore 3 enemy Shields"};
        this.favourLevel3 = new String[]{"3", "4", "Ignore 6 enemy Shields"};

    }

    @Override
    public void action(Player... players) {
        int tokens;
        switch (this.favourPower) {
            case 1:
                tokens = 2;
                break;
            case 2:
                tokens = 3;
                break;
            case 3:
                tokens = 4;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + this.favourPower);
        }
        if (players[0].getFavourTokens() - tokens < 0) {
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Ullr for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens), 0));
        System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 2:
                System.out.println("Through " + YELLOW + "Ullr's Aim, " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " ignores 2 Shields from " + players[1].getPlayerColour() + players[1].getName() + RESET);
                System.out.println(players[1].getPlayerColour() + players[1].getName() + RESET + " lost " + (Math.min(players[1].getShieldCounter(), 2)) + " Shields!");
                players[1].setShieldCounter(Math.max((players[1].getShieldCounter() - 2), 0));
                pressEnterToContinue();
                break;
            case 3:
                System.out.println("Through " + YELLOW + "Ullr's Aim, " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " ignores 3 Shields from " + players[1].getPlayerColour() + players[1].getName() + RESET);
                System.out.println(players[1].getPlayerColour() + players[1].getName() + RESET + " lost " + (Math.min(players[1].getShieldCounter(), 3)) + " Shields!");
                players[1].setShieldCounter(Math.max((players[1].getShieldCounter() - 3), 0));
                pressEnterToContinue();
                break;
            case 4:
                System.out.println("Through " + YELLOW + "Ullr's Aim, " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " ignores 6 Shields from " + players[1].getPlayerColour() + players[1].getName() + RESET);
                System.out.println(players[1].getPlayerColour() + players[1].getName() + RESET + " lost " + (Math.min(players[1].getShieldCounter(), 6)) + " Shields!");
                players[1].setShieldCounter(Math.max((players[1].getShieldCounter() - 6), 0));
                pressEnterToContinue();
                break;
        }
    }

}
