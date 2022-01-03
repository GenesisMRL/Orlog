package com.nunolima.gods;

import com.nunolima.game.Player;
import static com.nunolima.utils.Utilities.*;

public class Heimdall extends Sacred {

    public Heimdall() {

        this.priority = 4;
        this.selected = false;
        this.name = "HEIMDALL";
        this.summedUpInfo = "\u001b[4m6- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nHeal 1, 2 or 3 HP for each attack that you block.";
        this.favourLevel1 = new String[]{"1", "4", "Heal 1 HP per attack blocked"};
        this.favourLevel2 = new String[]{"2", "7", "Heal 2 HP per attack blocked"};
        this.favourLevel3 = new String[]{"3", "10", "Heal 3 HP per attack blocked"};

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
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Heimdall for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens), 0));
        int blocks = players[0].getArrowBlockCounter() + players[0].getAxeBlockCounter();
        System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 4:
                System.out.println(YELLOW + "Heimdall's Watch" + RESET + " heals " + players[0].getPlayerColour() + players[0].getName() + RESET + " by 1 Stone for every attack blocked");
                players[0].setHealth(Math.min(15, (players[0].getHealth() + blocks)));
                pressEnterToContinue();
                break;
            case 7:
                System.out.println(YELLOW + "Heimdall's Watch" + RESET + " heals " + players[0].getPlayerColour() + players[0].getName() + RESET + " by 2 Stones for every attack blocked");
                players[0].setHealth(Math.min(15, (players[0].getHealth() + (blocks * 2))));
                pressEnterToContinue();
                break;
            case 10:
                System.out.println(YELLOW + "Heimdall's Watch" + RESET + " heals " + players[0].getPlayerColour() + players[0].getName() + RESET + " by 3 Stones for every attack blocked");
                players[0].setHealth(Math.min(15, (players[0].getHealth() + (blocks * 3))));
                pressEnterToContinue();
                break;
        }
    }

}

