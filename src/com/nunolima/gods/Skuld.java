package com.nunolima.gods;

import com.nunolima.game.Player;
import static com.nunolima.utils.Utilities.*;

public class Skuld extends Sacred {

    public Skuld() {

        this.priority = 2;
        this.selected = false;
        this.name = "SKULD";
        this.summedUpInfo = "\u001b[4m11- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nDestroy 2, 3 or 4 enemy Tokens for every die that rolled an Arrow.";
        this.favourLevel1 = new String[]{"1", "4", "Destroy 2 enemy Tokens per rolled Arrow"};
        this.favourLevel2 = new String[]{"2", "6", "Destroy 3 enemy Tokens per rolled Arrow"};
        this.favourLevel3 = new String[]{"3", "8", "Destroy 4 enemy Tokens per rolled Arrow"};

    }

    @Override
    public void action(Player... players) {
        int tokens;
        switch (this.favourPower) {
            case 1:
                tokens = 4;
                break;
            case 2:
                tokens = 6;
                break;
            case 3:
                tokens = 8;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + this.favourPower);
        }
        if (players[0].getFavourTokens() - tokens < 0) {
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Skuld for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens),0));
        System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 4:
                System.out.println("Through " + YELLOW + "Skuld's Claim " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " destroys 2 of " + players[1].getPlayerColour() + players[1].getName() + "'s " + RESET + " \u001B[33mTokens\u001B[0m per Arrow die rolled.");
                System.out.println(players[1].getPlayerColour() + players[1].getName() + RESET + " lost " + (players[0].getArrowCounter() * 2) + " \u001B[33mTokens\u001B[0m.");
                players[1].setFavourTokens(Math.max(((players[1].getFavourTokens() - (players[0].getArrowCounter() * 2))),0));
                pressEnterToContinue();
                break;
            case 6:
                System.out.println("Through " + YELLOW + "Skuld's Claim " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " destroys 3 of " + players[1].getPlayerColour() + players[1].getName() + "'s " + RESET + " \u001B[33mTokens\u001B[0m per Arrow die rolled.");
                System.out.println(players[1].getPlayerColour() + players[1].getName() + RESET + " lost " + (players[0].getArrowCounter() * 3) + " \u001B[33mTokens\u001B[0m.");
                players[1].setFavourTokens(Math.max(((players[1].getFavourTokens() - (players[0].getArrowCounter() * 3))),0));
                pressEnterToContinue();
                break;
            case 8:
                System.out.println("Through " + YELLOW + "Skuld's Claim " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " destroys 4 of " + players[1].getPlayerColour() + players[1].getName() + "'s " + RESET + " \u001B[33mTokens\u001B[0m per Arrow die rolled.");
                System.out.println(players[1].getPlayerColour() + players[1].getName() + RESET + " lost " + (players[0].getArrowCounter() * 4) + " \u001B[33mTokens\u001B[0m.");
                players[1].setFavourTokens(Math.max(((players[1].getFavourTokens() - (players[0].getArrowCounter() * 4))),0));
                pressEnterToContinue();
                break;
        }
    }

}
