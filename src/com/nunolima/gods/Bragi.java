package com.nunolima.gods;

import com.nunolima.game.Player;
import static com.nunolima.utils.Utilities.*;

public class Bragi extends Sacred {

    public Bragi() {

        this.priority = 3;
        this.selected = false;
        this.name = "BRAGI";
        this.summedUpInfo = "\u001b[4m2- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nAdd 2, 3 or 4 extra Tokens for each Die that rolled a Hand.";
        this.favourLevel1 = new String[]{"1", "4", "Gain 2 Tokens per die that rolled a Hand"};
        this.favourLevel2 = new String[]{"2", "8", "Gain 3 Tokens per die that rolled a Hand"};
        this.favourLevel3 = new String[]{"3", "12", "Gain 4 Tokens per die that rolled a Hand"};

    }

    @Override
    public void action(Player... players) {
        int tokens;
        switch (this.favourPower) {
            case 1:
                tokens = 4;
                break;
            case 2:
                tokens = 8;
                break;
            case 3:
                tokens = 12;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + this.favourPower);
        }
        if (players[0].getFavourTokens() - tokens < 0) {
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Bragi for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens),0));
        System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 4:
                System.out.println(YELLOW + "Bragi's Verve" + RESET + " gives " + players[0].getPlayerColour() + players[0].getName() + RESET + " 2 \u001B[33mTokens\u001B[0m per Hand die rolled.");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (players[0].getHandCounter() * 2) + " \u001B[33mTokens\u001B[0m.");
                players[0].setFavourTokens(players[0].getFavourTokens() + (players[0].getHandCounter() * 2));
                pressEnterToContinue();
                break;
            case 8:
                System.out.println(YELLOW + "Bragi's Verve" + RESET + " gives " + players[0].getPlayerColour() + players[0].getName() + RESET + " 3 \u001B[33mTokens\u001B[0m per Hand die rolled.");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (players[0].getHandCounter() * 3) + " \u001B[33mTokens\u001B[0m.");
                players[0].setFavourTokens(players[0].getFavourTokens() + (players[0].getHandCounter() * 3));
                pressEnterToContinue();
                break;
            case 12:
                System.out.println(YELLOW + "Bragi's Verve" + RESET + " gives " + players[0].getPlayerColour() + players[0].getName() + RESET + " 4 \u001B[33mTokens\u001B[0m per Hand die rolled.");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (players[0].getHandCounter() * 4) + " \u001B[33mTokens\u001B[0m.");
                players[0].setFavourTokens(players[0].getFavourTokens() + (players[0].getHandCounter() * 4));
                pressEnterToContinue();
                break;
        }
    }
}
