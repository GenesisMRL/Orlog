package com.nunolima.gods;

import com.nunolima.game.Player;
import static com.nunolima.utils.Utilities.*;

public class Idunn extends Sacred {

    public Idunn() {

        this.priority = 5;
        this.selected = false;
        this.name = "IDUNN";
        this.summedUpInfo = "\u001b[4m8- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nHeal 2, 4 or 6 HP after resolution phase.";
        this.favourLevel1 = new String[]{"1", "4", "Heal 2 HP after resolution phase"};
        this.favourLevel2 = new String[]{"2", "7", "Heal 4 HP after resolution phase"};
        this.favourLevel3 = new String[]{"3", "10", "Heal 6 HP after resolution phase"};

    }

    @Override
    public void action(Player... players) {
        int tokens;
        switch (this.favourPower) {
            case 1:
                tokens = 4;
                break;
            case 2:
                tokens = 7;
                break;
            case 3:
                tokens = 10;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + this.favourPower);
        }
        if (players[0].getFavourTokens() - tokens < 0) {
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Idunn for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens), 0));
        System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 4:
                System.out.println(YELLOW + "Idunn's Rejuvenation" + RESET + " heals " + players[0].getPlayerColour() + players[0].getName() + RESET + " by 2 Stones");
                players[0].setHealth(Math.min(15, players[0].getHealth() + 2));
                pressEnterToContinue();
                break;
            case 7:
                System.out.println(YELLOW + "Idunn's Rejuvenation" + RESET + " heals " + players[0].getPlayerColour() + players[0].getName() + RESET + " by 4 Stones");
                players[0].setHealth(Math.min(15, players[0].getHealth() + 4));
                pressEnterToContinue();
                break;
            case 10:
                System.out.println(YELLOW + "Idunn's Rejuvenation" + RESET + " heals " + players[0].getPlayerColour() + players[0].getName() + RESET + " by 6 Stones");
                players[0].setHealth(Math.min(15, players[0].getHealth() + 6));
                pressEnterToContinue();
                break;
        }
    }
}

