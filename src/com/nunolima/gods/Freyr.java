package com.nunolima.gods;

import com.nunolima.game.Player;
import static com.nunolima.utils.Utilities.*;

public class Freyr extends Sacred {

    public Freyr() {

        this.priority = 3;
        this.selected = false;
        this.name = "FREYR";
        this.summedUpInfo = "\u001b[4m5- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nAdd 1, 2 or 3 Dice to the highest Face count";
        this.favourLevel1 = new String[]{"1", "4", "Add 2 dice to the highest Face count"};
        this.favourLevel2 = new String[]{"2", "6", "Add 3 dice to the highest Face count"};
        this.favourLevel3 = new String[]{"3", "8", "Add 4 dice to the highest Face count"};

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
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Freyr for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens),0));

        String highestCounter;
        switch (getLargestNumber(new int[]{players[0].getArrowCounter(), players[0].getAxeCounter(), players[0].getShieldCounter(), players[0].getHelmetCounter(), players[0].getHandCounter()})) {
            case 0:
                highestCounter = "Arrow";
                break;
            case 1:
                highestCounter = "Axe";
                break;
            case 2:
                highestCounter = "Shield";
                break;
            case 3:
                highestCounter = "Helmet";
                break;
            case 4:
                highestCounter = "Hand";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + getLargestNumber(new int[]{players[0].getArrowCounter(), players[0].getAxeCounter(), players[0].getShieldCounter(), players[0].getHelmetCounter(), players[0].getHandCounter()}));
        }
        System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 4:
                System.out.println("Through " + YELLOW + "Freyr's Gift " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " gets 2 extra dice.");
                switch (highestCounter) {
                    case "Arrow":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 2 extra Arrows!");
                        players[0].setArrowCounter(players[0].getArrowCounter() + 2);
                        break;
                    case "Axe":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 2 extra Axes!");
                        players[0].setAxeCounter(players[0].getAxeCounter() + 2);
                        break;
                    case "Shield":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 2 extra Shields!");
                        players[0].setShieldCounter(players[0].getShieldCounter() + 2);
                        break;
                    case "Helmet":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 2 extra Helmets!");
                        players[0].setHelmetCounter(players[0].getHelmetCounter() + 2);
                        break;
                    case "Hand":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 2 extra Hands!");
                        players[0].setHandCounter(players[0].getHandCounter() + 2);
                        break;
                }
                pressEnterToContinue();
                break;
            case 6:
                System.out.println("Through " + YELLOW + "Freyr's Gift " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " gets 3 extra dice.");
                switch (highestCounter) {
                    case "Arrow":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 3 extra Arrows!");
                        players[0].setArrowCounter(players[0].getArrowCounter() + 3);
                        break;
                    case "Axe":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 3 extra Axes!");
                        players[0].setAxeCounter(players[0].getAxeCounter() + 3);
                        break;
                    case "Shield":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 3 extra Shields!");
                        players[0].setShieldCounter(players[0].getShieldCounter() + 3);
                        break;
                    case "Helmet":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 3 extra Helmets!");
                        players[0].setHelmetCounter(players[0].getHelmetCounter() + 3);
                        break;
                    case "Hand":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 3 extra Hands!");
                        players[0].setHandCounter(players[0].getHandCounter() + 3);
                        break;
                }
                pressEnterToContinue();
                break;
            case 8:
                System.out.println("Through " + YELLOW + "Freyr's Gift " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " gets 4 extra dice.");
                switch (highestCounter) {
                    case "Arrow":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 4 extra Arrows!");
                        players[0].setArrowCounter(players[0].getArrowCounter() + 4);
                        break;
                    case "Axe":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 4 extra Axes!");
                        players[0].setAxeCounter(players[0].getAxeCounter() + 4);
                        break;
                    case "Shield":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 4 extra Shields!");
                        players[0].setShieldCounter(players[0].getShieldCounter() + 4);
                        break;
                    case "Helmet":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 4 extra Helmets!");
                        players[0].setHelmetCounter(players[0].getHelmetCounter() + 4);
                        break;
                    case "Hand":
                        System.out.println(players[0].getPlayerColour() + players[0].getName() + RESET + " got 4 extra Hands!");
                        players[0].setHandCounter(players[0].getHandCounter() + 4);
                        break;
                }
                pressEnterToContinue();
                break;
        }
    }

}
