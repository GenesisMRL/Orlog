package com.nunolima.gods;

import com.nunolima.game.Player;
import static com.nunolima.utils.Utilities.*;

public class Baldr extends Sacred {

    public Baldr() {

        this.priority = 3;
        this.selected = false;
        this.name = "BALDR";
        this.summedUpInfo = "\u001b[4m1- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nAdd 1, 2 or 3 Defenses for each die that rolled a Defense.";
        this.favourLevel1 = new String[]{"1", "3", "Double defense dice"};
        this.favourLevel2 = new String[]{"2", "6", "Triple defense dice"};
        this.favourLevel3 = new String[]{"3", "9", "Quadruple defense dice"};

    }

    @Override
    public void action(Player... players) {
        int tokens;
        switch (this.favourPower) {
            case 1:
                tokens = 3;
                break;
            case 2:
                tokens = 6;
                break;
            case 3:
                tokens = 9;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + this.favourPower);
        }
        if (players[0].getFavourTokens() - tokens < 0) {
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Baldr for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens),0));
        System.out.println(CLEAR + players[0].getPlayerColour() +players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 3:
                System.out.println("Through " + YELLOW + "Baldr's Invulnerability " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " adds 1 extra Defense for each rolled defense die.");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (players[0].getHelmetCounter())+ " extra Helmets!");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (players[0].getShieldCounter())+ " extra Shields!");
                players[0].setHelmetCounter(players[0].getHelmetCounter() * 2);
                players[0].setShieldCounter(players[0].getShieldCounter() * 2);
                pressEnterToContinue();
                break;
            case 6:
                System.out.println("Through " + YELLOW + "Baldr's Invulnerability " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " adds 2 extra Defenses for each rolled defense die.");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (players[0].getHelmetCounter() * 2)+ " extra Helmets!");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (players[0].getShieldCounter() * 2)+ " extra Shields!");
                players[0].setHelmetCounter(players[0].getHelmetCounter() * 3);
                players[0].setShieldCounter(players[0].getShieldCounter() * 3);
                pressEnterToContinue();
                break;
            case 9:
                System.out.println("Through " + YELLOW + "Baldr's Invulnerability " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " adds 3 extra Defenses for each rolled defense die.");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (players[0].getHelmetCounter() * 3)+ " extra Helmets!");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (players[0].getShieldCounter() * 3)+ " extra Shields!");
                players[0].setHelmetCounter(players[0].getHelmetCounter() * 4);
                players[0].setShieldCounter(players[0].getShieldCounter() * 4);
                pressEnterToContinue();
                break;
        }
    }
}

