package com.nunolima.gods;

import com.nunolima.game.Player;
import static com.nunolima.utils.Utilities.*;

public class Vidarr extends Sacred {

    public Vidarr() {

        this.priority = 3;
        this.selected = false;
        this.name = "VIDARR";
        this.summedUpInfo = "\u001b[4m14- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nIgnore 2, 3 or 6 enemy Helmets.";
        this.favourLevel1 = new String[]{"1", "2", "Ignore 2 enemy Helmets"};
        this.favourLevel2 = new String[]{"2", "4", "Ignore 4 enemy Helmets"};
        this.favourLevel3 = new String[]{"3", "6", "Ignore 6 enemy Helmets"};

    }

    @Override
    public void action(Player... players) {
        int tokens;
        switch (this.favourPower) {
            case 1:
                tokens = 2;
                break;
            case 2:
                tokens = 4;
                break;
            case 3:
                tokens = 6;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + this.favourPower);
        }
        if (players[0].getFavourTokens() - tokens < 0) {
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Vidarr for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens), 0));
        System.out.println(CLEAR + players[0].getName() + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 2:
                System.out.println(YELLOW + "Vidarr's Might" + RESET + " removes 2 Helmets from" + players[1].getPlayerColour() + players[1].getName() + RESET);
                System.out.println(players[1].getPlayerColour() + players[1].getName() + RESET + " lost " + (Math.min(players[1].getHelmetCounter(), 2)) + " Helmet!");
                players[1].setHelmetCounter(Math.max((players[1].getHelmetCounter() - 2), 0));
                pressEnterToContinue();
                break;
            case 4:
                System.out.println(YELLOW + "Vidarr's Might" + RESET + " removes 4 Helmets from" + players[1].getPlayerColour() + players[1].getName() + RESET);
                System.out.println(players[1].getPlayerColour() + players[1].getName() + RESET + " lost " + (Math.min(players[1].getHelmetCounter(), 4)) + " Helmet!");
                players[1].setHelmetCounter(Math.max((players[1].getHelmetCounter() - 4), 0));
                pressEnterToContinue();
                break;
            case 6:
                System.out.println(YELLOW + "Vidarr's Might" + RESET + " removes 6 Helmets" + players[1].getPlayerColour() + players[1].getName() + RESET);
                System.out.println(players[1].getPlayerColour() + players[1].getName() + RESET + " lost " + (Math.min(players[1].getHelmetCounter(), 6)) + " Helmet!");
                players[1].setHelmetCounter(Math.max((players[1].getHelmetCounter() - 6), 0));
                pressEnterToContinue();
                break;
        }
    }

}

