package com.nunolima.gods;

import com.nunolima.game.Player;

import static com.nunolima.utils.Utilities.*;

public class Mimir extends Sacred {

    public Mimir() {

        this.priority = 4;
        this.selected = false;
        this.name = "MIMIR";
        this.summedUpInfo = "\u001b[4m9- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nGain 1, 2 or 3 Tokens for each damage point taken.";
        this.favourLevel1 = new String[]{"1", "3", "Gain 1 Token per damage taken"};
        this.favourLevel2 = new String[]{"2", "5", "Gain 2 Tokens per damage taken"};
        this.favourLevel3 = new String[]{"3", "7", "Gain 3 Tokens per damage taken"};

    }

    @Override
    public void action(Player... players) {
        int tokens;
        switch (this.favourPower) {
            case 1:
                tokens = 3;
                break;
            case 2:
                tokens = 5;
                break;
            case 3:
                tokens = 7;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + this.favourPower);
        }
        if (players[0].getFavourTokens() - tokens < 0) {
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Mimir for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens), 0));
        int takenHits = players[1].getArrowHitCounter() + players[1].getAxeHitCounter();
        System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 3:
                System.out.println(YELLOW + "Mimir's Wisdom" + RESET + " gives " + players[0].getPlayerColour() + players[0].getName() + RESET + " 1 \u001B[33mToken\u001B[0m per damage taken.\n");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + takenHits + " \u001B[33mTokens\u001B[0m.");
                players[0].setFavourTokens(players[0].getFavourTokens() + takenHits);
                pressEnterToContinue();
                break;
            case 5:
                System.out.println(YELLOW + "Mimir's Wisdom" + RESET + " gives " + players[0].getPlayerColour() + players[0].getName() + RESET + " 2 \u001B[33mTokens\u001B[0m per damage taken.\n");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (takenHits * 2) + " \u001B[33mTokens\u001B[0m.");
                players[0].setFavourTokens(players[0].getFavourTokens() + (takenHits * 2));
                pressEnterToContinue();
                break;
            case 7:
                System.out.println(YELLOW + "Mimir's Wisdom" + RESET + " gives " + players[0].getPlayerColour() + players[0].getName() + RESET + " 3 \u001B[33mTokens\u001B[0m per damage taken.\n");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (takenHits * 3) + " \u001B[33mTokens\u001B[0m.");
                players[0].setFavourTokens(players[0].getFavourTokens() + (takenHits * 3));
                pressEnterToContinue();
                break;
        }
    }

}
