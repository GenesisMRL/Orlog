package com.nunolima.gods;

import com.nunolima.game.Player;
import static com.nunolima.utils.Utilities.*;

public class Skadi extends Sacred {

    public Skadi() {

        this.priority = 3;
        this.selected = false;
        this.name = "SKADI";
        this.summedUpInfo = "\u001b[4m10- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nAdd 1, 2 or 3 Arrows for each die that rolled an Arrow.";
        this.favourLevel1 = new String[]{"1", "6", "Add 1 Arrow per Arrow rolled"};
        this.favourLevel2 = new String[]{"2", "10", "Add 2 Arrows per Arrow rolled"};
        this.favourLevel3 = new String[]{"3", "14", "Add 3 Arrows per Arrow rolled"};

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
                tokens = 14;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + this.favourPower);
        }
        if (players[0].getFavourTokens() - tokens < 0) {
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Skadi for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens),0));
        System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 6:
                System.out.println("Through " + YELLOW + "Skadi's Hunt " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " adds 1 extra Arrow for each rolled Arrow die.");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (players[0].getArrowCounter())+ " extra Arrows!");
                players[0].setArrowCounter(players[0].getArrowCounter() * 2);
                pressEnterToContinue();
                break;
            case 10:
                System.out.println("Through " + YELLOW + "Skadi's Hunt " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " adds 2 extra Arrows for each rolled Arrow die.");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (players[0].getArrowCounter() * 2)+ " extra Arrows!");
                players[0].setArrowCounter(players[0].getArrowCounter() * 3);
                pressEnterToContinue();
                break;
            case 14:
                System.out.println("Through " + YELLOW + "Skadi's Hunt " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " adds 3 extra Arrows for each rolled Arrow die.");
                System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got " + (players[0].getArrowCounter() * 3)+ " extra Arrows!");
                players[0].setArrowCounter(players[0].getArrowCounter() * 4);
                pressEnterToContinue();
                break;
        }
    }

}
