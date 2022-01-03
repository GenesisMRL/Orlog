package com.nunolima.gods;

import com.nunolima.game.Player;
import static com.nunolima.utils.Utilities.*;

public class Hel extends Sacred {


    public Hel() {

        this.priority = 4;
        this.selected = false;
        this.name = "HEL";
        this.summedUpInfo = "\u001b[4m7- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nHeal 1, 2 or 3 HP for every Axe that hits the enemy.";
        this.favourLevel1 = new String[]{"1", "6", "Heal 1 HP per Axe hit"};
        this.favourLevel2 = new String[]{"2", "12", "Heal 2 HP per Axe hit"};
        this.favourLevel3 = new String[]{"3", "18", "Heal 3 HP per Axe hit"};

    }

    @Override
    public void action(Player... players) {
        int tokens;
        switch (this.favourPower) {
            case 1:
                tokens = 6;
                break;
            case 2:
                tokens = 12;
                break;
            case 3:
                tokens = 18;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + this.favourPower);
        }
        if (players[0].getFavourTokens() - tokens < 0) {
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Hel for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens), 0));
        int hits = players[0].getAxeHitCounter();
        System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 6:
                System.out.println(YELLOW + "Hel's Grip" + RESET + " heals " + players[0].getPlayerColour() + players[0].getName() + RESET + " by 1 Stone for every Axe that successfully struck");
                players[0].setHealth(Math.min(15, players[0].getHealth() + hits));
                pressEnterToContinue();
                break;
            case 12:
                System.out.println(YELLOW + "Hel's Grip" + RESET + " heals " + players[0].getPlayerColour() + players[0].getName() + RESET + " by 2 Stones for every Axe that successfully struck");
                players[0].setHealth(Math.min(15, players[0].getHealth() + (hits * 2)));
                pressEnterToContinue();
                break;
            case 18:
                System.out.println(YELLOW + "Hel's Grip" + RESET + " heals " + players[0].getPlayerColour() + players[0].getName() + RESET + " by 3 Stones for every Axe that successfully struck");
                players[0].setHealth(Math.min(15, players[0].getHealth() + (hits * 3)));
                pressEnterToContinue();
                break;
        }
    }

}
