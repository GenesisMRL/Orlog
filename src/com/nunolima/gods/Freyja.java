package com.nunolima.gods;

import com.nunolima.game.Die;
import com.nunolima.game.Player;

import static com.nunolima.utils.Utilities.*;

public class Freyja extends Sacred {

    public Freyja() {

        this.priority = 1;
        this.selected = false;
        this.name = "FREYJA";
        this.summedUpInfo = "\u001b[4m4- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nRoll 1, 2 or 3 additional dice in a round.";
        this.favourLevel1 = new String[]{"1", "2", "Roll 1 additional Die this round"};
        this.favourLevel2 = new String[]{"2", "4", "Roll 2 additional Dice this round"};
        this.favourLevel3 = new String[]{"3", "6", "Roll 3 additional Dice this round"};

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
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Freyja for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens), 0));
        Die randomDie1 = players[0].getDiceSet().get((int) (Math.random() * players[0].getDiceSet().size()));
        String randomDieFace1 = randomDie1.getFaces()[(int) (Math.random() * randomDie1.getFaces().length)];
        Die randomDie2 = players[0].getDiceSet().get((int) (Math.random() * players[0].getDiceSet().size()));
        String randomDieFace2 = randomDie2.getFaces()[(int) (Math.random() * randomDie2.getFaces().length)];
        Die randomDie3 = players[0].getDiceSet().get((int) (Math.random() * players[0].getDiceSet().size()));
        String randomDieFace3 = randomDie3.getFaces()[(int) (Math.random() * randomDie3.getFaces().length)];
        System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 2:
                System.out.println("Through " + YELLOW + "Freyja's Plenty " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " rolls 1 extra random die.\n");
                System.out.println(randomDieFace1 + " added to " + players[0].getPlayerColour() + players[0].getName() + "'s " + RESET + "dice!");
                players[0].addDieFaceToSelection(randomDieFace1);
                pressEnterToContinue();
                break;
            case 4:
                System.out.println("Through " + YELLOW + "Freyja's Plenty " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " rolls 2 extra random dice.\n");
                System.out.println(randomDieFace1 + " added to " + players[0].getPlayerColour() + players[0].getName() + "'s " + RESET + "dice!");
                System.out.println(randomDieFace2 + " added to " + players[0].getPlayerColour() + players[0].getName() + "'s " + RESET + "dice!");
                players[0].addDieFaceToSelection(randomDieFace1);
                players[0].addDieFaceToSelection(randomDieFace2);
                pressEnterToContinue();
                break;
            case 6:
                System.out.println("Through " + YELLOW + "Freyja's Plenty " + RESET + players[0].getPlayerColour() + players[0].getName() + RESET + " rolls 3 extra random dice.\n");
                System.out.println(randomDieFace1 + " added to " + players[0].getPlayerColour() + players[0].getName() + "'s " + RESET + "dice!");
                System.out.println(randomDieFace2 + " added to " + players[0].getPlayerColour() + players[0].getName() + "'s " + RESET + "dice!");
                System.out.println(randomDieFace3 + " added to " + players[0].getPlayerColour() + players[0].getName() + "'s " + RESET + "dice!");
                players[0].addDieFaceToSelection(randomDieFace1);
                players[0].addDieFaceToSelection(randomDieFace2);
                players[0].addDieFaceToSelection(randomDieFace3);
                pressEnterToContinue();
                break;
        }
    }

}