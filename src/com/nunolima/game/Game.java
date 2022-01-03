package com.nunolima.game;

import com.nunolima.gods.*;

import static com.nunolima.game.Leaderboard.*;
import static com.nunolima.utils.LeaderboardManager.*;
import static com.nunolima.utils.Utilities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private static final Player player1 = new Player(BLUE);
    private static final Player player2 = new Player(RED);
    private static int round = 1;
    private static int roll = 1;
    private static Leaderboard leaderboard;

    static {
        try {
            leaderboard = new Leaderboard(deserialize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void menuSection() {
        printCover();
        String choice = checkIfValidInput(1, 5);
        switch (choice) {
            case "1":
                gameSequence();
                break;
            case "2":
                onlineGame();
                break;
            case "3":
                printLeaderboard();
                menuSection();
                break;
            case "4":
                printRules();
                break;
            case "5":
                System.out.println("See you soon!");
                serialize(leaderboard);
                System.exit(0);
        }
    }

    private static void gameSequence() {

        //-----INITIATION PHASE-----//

        namePlayer(player1, player1.getPlayerColour());
        namePlayer(player2, player2.getPlayerColour());

        chooseGods(player1);
        pressEnterToContinue();

        chooseGods(player2);
        pressEnterToContinue();

        //-----PLAY PHASE_____//

        while (player1.getHealth() > 0 & player2.getHealth() > 0) {
            roundSequence(player1, player2);
            roundSequence(player2, player1);
        }
    }

    private static void roundSequence(Player firstPlayer, Player secondPlayer) {
        printRound();
        System.out.println(firstPlayer.getPlayerColour() + firstPlayer.getName() + " goes first this round!" + RESET + "\n");

        firstPlayer.printPlayerHPAndTokensTable();
        secondPlayer.printPlayerHPAndTokensTable();
        pressEnterToContinue();

        rollSequence(firstPlayer, secondPlayer);

        selectGodFavour(firstPlayer, secondPlayer);
        selectGodFavour(secondPlayer, firstPlayer);

        firstPlayer.updateTokens();
        secondPlayer.updateTokens();

        firstPlayer.resetDiceSet();
        secondPlayer.resetDiceSet();

        useGodFavour(firstPlayer, secondPlayer, 1);
        useGodFavour(secondPlayer, firstPlayer, 1);

        firstPlayer.updateCounters();
        secondPlayer.updateCounters();

        useGodFavour(firstPlayer, secondPlayer, 2);
        useGodFavour(secondPlayer, firstPlayer, 2);

        useGodFavour(firstPlayer, secondPlayer, 3);
        useGodFavour(secondPlayer, firstPlayer, 3);

        firstPlayer.updateHitsAndBlocks(secondPlayer);

        printDiceVSDice(firstPlayer, secondPlayer);
        resolutionPhase(firstPlayer, secondPlayer);

        useGodFavour(firstPlayer, secondPlayer, 4);
        useGodFavour(secondPlayer, firstPlayer, 4);

        useGodFavour(firstPlayer, secondPlayer, 5);
        useGodFavour(secondPlayer, firstPlayer, 5);

        firstPlayer.resetCounters();
        secondPlayer.resetCounters();

        firstPlayer.resetSelectedDiceFaces();
        secondPlayer.resetSelectedDiceFaces();

        round++;
    }

    private static void resolutionPhase(Player firstPlayer, Player secondPlayer) {


        //Hand steals
        if (secondPlayer.getFavourTokens() - firstPlayer.getHandCounter() < 0) {
            firstPlayer.setFavourTokens(firstPlayer.getFavourTokens() + secondPlayer.getFavourTokens());
            secondPlayer.setFavourTokens(0);
        } else {
            firstPlayer.setFavourTokens(firstPlayer.getFavourTokens() + firstPlayer.getHandCounter());
            secondPlayer.setFavourTokens(secondPlayer.getFavourTokens() - firstPlayer.getHandCounter());
        }

        if (firstPlayer.getFavourTokens() - secondPlayer.getHandCounter() < 0) {
            secondPlayer.setFavourTokens(secondPlayer.getFavourTokens() + firstPlayer.getFavourTokens());
            firstPlayer.setFavourTokens(0);
        } else {
            secondPlayer.setFavourTokens(secondPlayer.getFavourTokens() + secondPlayer.getHandCounter());
            firstPlayer.setFavourTokens(firstPlayer.getFavourTokens() - secondPlayer.getHandCounter());
        }

        System.out.println(CLEAR + "\u001b[4mRESOLUTION:\n\n" + RESET);
        System.out.println(firstPlayer.getPlayerColour() + firstPlayer.getName() + RESET + " hits " + secondPlayer.getPlayerColour() + secondPlayer.getName() + RESET + " with " + firstPlayer.getAxeHitCounter() + " Axes");
        System.out.println(firstPlayer.getPlayerColour() + firstPlayer.getName() + RESET + " hits " + secondPlayer.getPlayerColour() + secondPlayer.getName() + RESET + " with " + firstPlayer.getArrowHitCounter() + " Arrows");
        System.out.println(secondPlayer.getPlayerColour() + secondPlayer.getName() + RESET + " hits " + firstPlayer.getPlayerColour() + firstPlayer.getName() + RESET + " with " + secondPlayer.getAxeHitCounter() + " Axes");
        System.out.println(secondPlayer.getPlayerColour() + secondPlayer.getName() + RESET + " hits " + firstPlayer.getPlayerColour() + firstPlayer.getName() + RESET + " with " + secondPlayer.getArrowHitCounter() + " Arrows");
        System.out.println(firstPlayer.getPlayerColour() + firstPlayer.getName() + RESET + " steals " + firstPlayer.getHandCounter() + " \u001B[33mTokens\u001B[0m from " + secondPlayer.getPlayerColour() + secondPlayer.getName() + RESET);
        System.out.println(secondPlayer.getPlayerColour() + secondPlayer.getName() + RESET + " steals " + secondPlayer.getHandCounter() + " \u001B[33mTokens\u001B[0m from " + firstPlayer.getPlayerColour() + firstPlayer.getName() + RESET + "\n");

        pressEnterToContinue();

        secondPlayer.setHealth(secondPlayer.getHealth() - (firstPlayer.getAxeHitCounter()));
        secondPlayer.checkIfLostAgainst(firstPlayer);

        secondPlayer.setHealth(secondPlayer.getHealth() - (firstPlayer.getArrowHitCounter()));
        secondPlayer.checkIfLostAgainst(firstPlayer);

        firstPlayer.setHealth(firstPlayer.getHealth() - (secondPlayer.getAxeHitCounter()));
        firstPlayer.checkIfLostAgainst(secondPlayer);

        firstPlayer.setHealth(firstPlayer.getHealth() - (secondPlayer.getArrowHitCounter()));
        firstPlayer.checkIfLostAgainst(secondPlayer);
    }

    /**
     * INITIALIZERS
     **/

    private static void namePlayer(Player player, String playerColour) {
        System.out.println(playerColour + "\nPlayer, choose your name:" + RESET);
        String playerName = sc.nextLine();
        player.setName(playerName);
        System.out.println();
    }

    private static void chooseGods(Player player) {
        List<Sacred> godList = Arrays.asList(new Baldr(), new Bragi(), new Brunhild(), new Freyja(), new Freyr(),
                new Heimdall(), new Hel(), new Idunn(), new Mimir(), new Skadi(), new Skuld(), new Thor(), new Ullr(), new Vidarr());

        System.out.println(CLEAR + player.getPlayerColour() + player.getName() + ", please choose 3 Gods to help you in battle!" + RESET);
        pressEnterToContinue();

        printAvailableGods(godList);

        System.out.println("choice 1:");
        String choice1 = checkIfValidInput(1, 14);
        player.getGods().add(godList.get(Integer.parseInt(choice1) - 1));

        System.out.println("choice 2:");
        String choice2 = checkIfValidInput(1, 14);
        while (choice2.equals(choice1)) {
            System.out.println("\nInvalid Input. Try again:");
            choice2 = checkIfValidInput(1, 14);
        }
        player.getGods().add(godList.get(Integer.parseInt(choice2) - 1));

        System.out.println("choice 3:");
        String choice3 = checkIfValidInput(1, 14);
        while (choice3.equals(choice1) || choice3.equals(choice2)) {
            System.out.println("\nInvalid Input. Try again:");
            choice3 = checkIfValidInput(1, 14);
        }
        player.getGods().add(godList.get(Integer.parseInt(choice3) - 1));

        player.printGodsSummedUp();
    }

    /**
     * ROLL_MECHANICS
     **/

    private static void rollSequence(Player firstPlayer, Player secondPlayer) {

        printRoll(firstPlayer);
        ArrayList<String> fpRoll1 = rollDice(firstPlayer);
        rollAction(firstPlayer, fpRoll1);

        printRoll(secondPlayer);
        ArrayList<String> spRoll1 = rollDice(secondPlayer);
        rollAction(secondPlayer, spRoll1);

        roll++;

        printRoll(firstPlayer);
        ArrayList<String> fpRoll2 = rollDice(firstPlayer);
        rollAction(firstPlayer, fpRoll2);

        printRoll(secondPlayer);
        ArrayList<String> spRoll2 = rollDice(secondPlayer);
        rollAction(secondPlayer, spRoll2);

        roll++;

        printRoll(firstPlayer);
        ArrayList<String> fpRoll3 = rollDice(firstPlayer);
        rollAction(firstPlayer, fpRoll3, true);

        printRoll(secondPlayer);
        ArrayList<String> spRoll3 = rollDice(secondPlayer);
        rollAction(secondPlayer, spRoll3, true);

        System.out.println(CLEAR);
        firstPlayer.printSelectedDiceFaces();
        secondPlayer.printSelectedDiceFaces();

        roll = 1;

        pressEnterToContinue();

    }

    private static void rollAction(Player player, ArrayList<String> rolledDice, boolean... roll3) {
        if (rolledDice.size() == 0) {
            return;
        }
        if (roll3.length != 0) {
            if (roll3[0]) {
                System.out.println("Last roll, all remaining dice will be randomly rolled and selected!\n");
                for (String dieFace : rolledDice) {
                    System.out.println("Added ---> " + dieFace + " to selection");
                    player.addDieFaceToSelection(dieFace);
                }
                pressEnterToContinue();
                return;
            }
        }

        System.out.println(CLEAR + player.getPlayerColour() + player.getName() + RESET + ", choose the Dice you want to keep with the numbers. use '0' to skip:\n");

        System.out.println("\n\n\u001b[4mAvailable Dice:\n" + RESET);
        for (int i = 0; i < rolledDice.size(); i++)
            System.out.println("Die " + (i + 1) + ": " + rolledDice.get(i));

        if (player.getSelectedDiceFaces().size() > 0) {

            System.out.println("\n\n\u001b[4mKept Dice:\n" + RESET);
            for (String dieFace : player.getSelectedDiceFaces())
                System.out.println(dieFace);
        }

        String choice = checkIfValidInput(0, rolledDice.size());
        if (Integer.parseInt(choice) == 0) {
            return;
        }
        player.addDieFaceToSelection(rolledDice.get(Integer.parseInt(choice) - 1));
        rolledDice.remove(Integer.parseInt(choice) - 1);
        player.getDiceSet().remove(Integer.parseInt(choice) - 1);
        rollAction(player, rolledDice);

    }

    private static ArrayList<String> rollDice(Player player) {

        ArrayList<String> tempList = new ArrayList<>();

        for (int i = 0; i < player.getDiceSet().size(); i++) {
            tempList.add(player.getDiceSet().get(i).getFaces()[(int) (Math.random() * player.getDiceSet().get(0).getFaces().length)]);
        }
        return tempList;
    }

    /**
     * GOD_FAVOUR_TOOLS
     **/

    private static void selectGodFavour(Player currentPlayer, Player opposingPlayer) {
        currentPlayer.printGodsFavours();
        currentPlayer.printSimplePlayerHPAndTokens();
        opposingPlayer.printSimplePlayerHPAndTokens();
        System.out.println("\n" + currentPlayer.getPlayerColour() + currentPlayer.getName() + RESET + ", do you want to use a  God Favour?:\n");
        System.out.println("1- Yes\n2- No");
        String choice = checkIfValidInput(1, 2);

        if (Integer.parseInt(choice) == 2) {
            return;
        }

        System.out.println("What God do you want?");
        String a = checkIfValidInput(1, 3);
        switch (a) {
            case "1":
                currentPlayer.getGods().get(0).setSelected(true);
                System.out.println("What level do you want to use?");
                String b = checkIfValidInput(1, 3);
                currentPlayer.getGods().get(0).setFavourPower(Integer.parseInt(b));
                break;
            case "2":
                currentPlayer.getGods().get(1).setSelected(true);
                System.out.println("What level do you want to use?");
                String c = checkIfValidInput(1, 3);
                currentPlayer.getGods().get(1).setFavourPower(Integer.parseInt(c));
                break;
            case "3":
                currentPlayer.getGods().get(2).setSelected(true);
                System.out.println("What level do you want to use?");
                String d = checkIfValidInput(1, 3);
                currentPlayer.getGods().get(2).setFavourPower(Integer.parseInt(d));
                break;
        }

    }

    private static void useGodFavour(Player attackingPlayer, Player defendingPlayer, int priority) {
        for (int i = 0; i < attackingPlayer.getGods().size(); i++) {
            if (attackingPlayer.getGods().get(i).isSelected() & attackingPlayer.getGods().get(i).getPriority() == priority) {
                System.out.println(CLEAR);
                attackingPlayer.getGods().get(i).action(attackingPlayer, defendingPlayer);
                attackingPlayer.getGods().get(i).setSelected(false);
            }
        }
    }

    /**
     * PRINTS
     **/

    private static void onlineGame() {
        System.out.println("Coming soon!");
        pressEnterToContinue();
        menuSection();
    }

    private static void printCover() {
        System.out.println(CLEAR + RED_BACKGROUND + BLACK +
                "                                                                                          \n" +
                "                        :             -+.                                                 \n" +
                "          +@%-          %%+.          -@@*:               =@@+         :+%-        +#=.   \n" +
                "        =%@**@%-        %@#@#-        -@@@@%=           =%@*+@@=       .#@@:      =@@+    \n" +
                "     .+@@+.  .*@%=      %@ .+%@*-     :@@ =%@@*:      =%@*.   +@@+       -@@=    *@%.     \n" +
                "    +@@*.      .*@%-    %@    -@@@-   :@%   =%@@%.  =@@#.       =@@+      .%@+  #@*       \n" +
                "    -#@%=      =%@*.    %@  :*@@+.    :@%     -+.   :#@@+.     -#@#:        *@#%@=        \n" +
                "      :#@@+. =%@+.      @@=%@%=       :@%             .*@@*: -%@#:           #@@+         \n" +
                "        :#@@@@*.        @@@@=         :@%               .*@@@@#:            *@%@@=        \n" +
                "         +@@@@#-        @@+%@@+:      :@%                =%@@@%-          :%@# .%@#.      \n" +
                "      :*@@*:.*@@%=      @@. -*@@%+.   -@@             .+@@#:.+@@@+.      =@@*   .#@@-     \n" +
                "    :#@@%:    .#@@@=   .@@:    =@@*   =@@           .#@@%-    .*@@@*   .%@@=      *@@*    \n" +
                "     =%-        :#+.   -@@-      .    +@@.           =%=        .**:   :+%=        *#+.   \n" +
                "                        ..            =++:                                                \n" +
                "                                                                                          \n" +
                "                                                                                          \n" +
                "                                                                                          \n");
        System.out.println("Welcome to Orlog! What do you want to do?\n");
        System.out.println("\u001b[4m1- Play Locally");
        System.out.println("2- Play Online");
        System.out.println("3- Leaderboards");
        System.out.println("4- Game Rules");
        System.out.println("5- Quit Game\n\n\n\n\n\n\n\n\n\n\n\n\n" + RESET);

    }

    private static void printRules() {
        System.out.println(CLEAR + "Orlog is a two Player dice game. Each player begins with 6 dice, 15 HP, and can select 3 Gods to assist them.");
        System.out.println("The game is divided into 3 main Phases, which will now be described:\n\n");

        System.out.println("---------------------");
        System.out.println("\u001b[4mINITIALIZATION PHASE" + RESET);
        System.out.println("---------------------\n\n");
        System.out.println("In this Phase, each player simply chooses a name and 3 Gods from the available list.\n\n");
        System.out.println("A future update will support custom games with more Gods, dice and HP!");

        System.out.println("---------------------");
        System.out.println("\u001b[4mROLLING PHASE" + RESET);
        System.out.println("---------------------\n\n");

        System.out.println("For this Phase, Players will roll their dice.");
        System.out.println("Each player has a maximum of 3 rolls, and in each roll they can select any dice they may want to keep.");
        System.out.println("By round 3, if a Player didn't select all 6 dice, all remaining dice are automatically added.");
        System.out.println("Each die has 5 possible Faces, and they work as follows:\n\n");
        System.out.println("\u001b[4mAxe" + RESET + " --------> Deals 1 damage to enemy Player");
        System.out.println("\u001b[4mArrow" + RESET + " ------> Deals 1 damage to enemy Player");
        System.out.println("\u001b[4mHelmet" + RESET + " -----> Blocks 1 damage from enemy Axe");
        System.out.println("\u001b[4mShield" + RESET + " -----> Blocks 1 damage from enemy Arrow");
        System.out.println("\u001b[4mHand" + RESET + " -------> Steals 1 \u001B[33mToken\u001B[0m from enemy Player\n");
        System.out.println("Aditionally, each die has 2 faces with the \u001B[33mToken\u001B[0m marking.");
        System.out.println("Selected \u001B[33mTokens\u001B[0m are kept to be used in the next Phase.\n\n");

        System.out.println("---------------------");
        System.out.println("\u001b[4mGOD FAVOUR PHASE" + RESET);
        System.out.println("---------------------\n\n");

        System.out.println("In this Phase, Players choose whether they wish to use a God Favour for this round.");
        System.out.println("All God Favour have a different action and each action has 3 different Power Levels.");
        System.out.println("Depending on the action, God Favours have different priorities and take place at different times.");
        System.out.println("To use the God Favours, the Player must spend the \u001B[33mTokens\u001B[0m corresponding to the selected Power Level\n\n");
        System.out.println("Every round, the Player that went first goes second and vice-versa.");
        System.out.println("Now get out there and dominate your opponents!\n\n");
        System.out.println("Press Enter to return to the Menu:");
        sc.nextLine();
        menuSection();

    }

    private static void printRound() {
        System.out.println(CLEAR + "--------------------------------");
        System.out.println("ROUND " + round);
        System.out.println("--------------------------------\n");
    }

    private static void printRoll(Player player) {
        System.out.println(CLEAR + "\n------------------------");
        System.out.println("ROLL " + roll + ": " + player.getPlayerColour() + player.getName() + RESET + "\n");
        System.out.println("------------------------");
        pressEnterToContinue();
    }

    private static void printAvailableGods(List<Sacred> godList) {

        for (Sacred god : godList) {
            god.printInfo();
        }
        System.out.println("");
    }

    private static void printDiceVSDice(Player firstPlayer, Player secondPlayer) {
        String heading1 = "FACES";
        String heading2 = "AMMOUNT";
        String heading3 = "AMMOUNT";
        String heading4 = "FACES";
        String divider = "------------------------------------------------------------------------------------------------------------";
        System.out.println(CLEAR + "COMPARISON TABLE");
        System.out.println(WHITE_BACKGROUND + BLACK);
        System.out.printf("%-15s %15s %30s %45s %n", firstPlayer.getName(), "", "", secondPlayer.getName());
        System.out.println(divider);
        System.out.printf("%-15s %15s %30s %45s %n", heading1, heading2, heading3, heading4);
        System.out.println(divider);
        System.out.printf("%-15s %15s %30s %45s %n", "AXES", firstPlayer.getAxeCounter(), secondPlayer.getHelmetCounter(), "HELMETS");
        System.out.printf("%-15s %15s %30s %45s %n", "ARROWS", firstPlayer.getArrowCounter(), secondPlayer.getShieldCounter(), "SHIELDS");
        System.out.printf("%-15s %15s %30s %45s %n", "HELMETS", firstPlayer.getHelmetCounter(), secondPlayer.getAxeCounter(), "AXES");
        System.out.printf("%-15s %15s %30s %45s %n", "SHIELDS", firstPlayer.getShieldCounter(), secondPlayer.getArrowCounter(), "ARROWS");
        System.out.printf("%-15s %15s %30s %45s %n", "HANDS", firstPlayer.getHandCounter(), secondPlayer.getHandCounter(), "HANDS");
        System.out.println(divider + "\n" + RESET);
        pressEnterToContinue();
    }

    public static Leaderboard getLeaderboard() {
        return leaderboard;
    }
}
