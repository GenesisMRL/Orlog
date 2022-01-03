package com.nunolima.gods;

import com.nunolima.game.Player;
import static com.nunolima.utils.Utilities.*;

public class Thor extends Sacred {

    public Thor() {

        this.priority = 4;
        this.selected = false;
        this.name = "THOR";
        this.summedUpInfo = "\u001b[4m12- " + this.name + ":" + RESET + BLACK + YELLOW_BACKGROUND + "\n\nDeal 2, 5 or 8 damage to the enemy after Resolution Phase.";
        this.favourLevel1 = new String[]{"1", "4", "Deal 2 damage to the enemy"};
        this.favourLevel2 = new String[]{"2", "8", "Deal 5 damage to the enemy"};
        this.favourLevel3 = new String[]{"3", "12", "Deal 8 damage to the enemy"};

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
            System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " calls to Thor for help but there was not enough devotion!");
            return;
        }
        players[0].setFavourTokens(Math.max((players[0].getFavourTokens() - tokens),0));
        System.out.println(CLEAR + players[0].getPlayerColour() + players[0].getName() + RESET + " spent " + tokens + " \u001B[33mTokens\u001B[0m!");
        switch (tokens) {
            case 4:
                System.out.println(YELLOW + "Thor's Strike" + RESET + " hits " + players[1].getPlayerColour() + players[1].getName() + RESET + " for 2 damage");
                players[1].setHealth(Math.max((players[1].getHealth() - 2),0));
                pressEnterToContinue();
                players[1].checkIfLostAgainst(players[0]);
                break;
            case 8:
                System.out.println(YELLOW + "Thor's Strike" + RESET + " hits " + players[1].getPlayerColour() + players[1].getName() + RESET + " for 5 damage");
                players[1].setHealth(Math.max((players[1].getHealth() - 5),0));
                pressEnterToContinue();
                players[1].checkIfLostAgainst(players[0]);
                break;
            case 12:
                System.out.println(YELLOW + "Thor's Strike" + RESET + " hits " + players[1].getPlayerColour() + players[1].getName() + RESET + " for 8 damage");
                players[1].setHealth(Math.max((players[1].getHealth() - 8),0));
                pressEnterToContinue();
                players[1].checkIfLostAgainst(players[0]);
                break;
        }

    }

}
