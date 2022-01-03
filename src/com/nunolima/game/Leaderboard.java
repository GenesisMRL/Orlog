package com.nunolima.game;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static com.nunolima.utils.Utilities.*;

public class Leaderboard {

    private static Map<String, Integer> leaderboard = new HashMap<>();

    public Leaderboard(HashMap<String, Integer> leaderboard) {

        Leaderboard.leaderboard = leaderboard;
    }

    public static void addPlayerToLeaderboard(String playerName) {
        if (leaderboard != null) {
            if (leaderboard.containsKey(playerName)) {
                leaderboard.replace(playerName, leaderboard.get(playerName), leaderboard.get(playerName) + 1);
                return;
            }
            leaderboard.put(playerName, 1);
            return;
        }
        leaderboard = new HashMap<>();
        leaderboard.put(playerName, 1);
    }

    public static void printLeaderboard() {
        String heading1 = "PLAYER";
        String heading2 = "WINS";
        String divider = "------------------------------------------------------------";
        System.out.println(CLEAR + WHITE_BACKGROUND + BLACK);
        System.out.println(divider);
        System.out.printf("%15s %30s %n", heading1, heading2);
        System.out.println(divider);

        leaderboard.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed())
                .map(Map.Entry::getKey)
                .forEach(o -> System.out.printf("%15s %30s %n", o, leaderboard.get(o)));

        System.out.println(divider + "\n" + RESET);
        pressEnterToContinue();
    }

    public Map<String, Integer> getLeaderboard() {
        return leaderboard;
    }
}
