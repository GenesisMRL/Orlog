package com.nunolima.utils;

import com.nunolima.game.Leaderboard;

import java.io.*;
import java.util.HashMap;

public class LeaderboardManager {

    public static void serialize(Leaderboard leaderboard) {
        HashMap<String, Integer> outMap = new HashMap<>(leaderboard.getLeaderboard());
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("leaderboard.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(outMap);
            out.close();
            fileOut.close();
            System.out.println("Serialized Leaderboard saved in /tmp/leaderboard.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static HashMap<String,Integer> deserialize() throws IOException {
        HashMap<String,Integer> leaderboard= new HashMap<>();
            FileInputStream fileIn = new FileInputStream("leaderboard.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            leaderboard = (HashMap<String, Integer>) in.readObject();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Leaderboard class not found");
            c.printStackTrace();
            return null;
        } finally {
            in.close();
            fileIn.close();
        }
        return  new HashMap<>(leaderboard);
    }
}
