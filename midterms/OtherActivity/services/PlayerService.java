package midterms.OtherActivity.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import midterms.OtherActivity.models.Player;

public class PlayerService {
    public static List<Player> loadUsers() {
        List<Player> playerList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("midterms/OtherActivity/data/Players.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                String username = data[0];
                String password = data[1];

                int highestScore = 0;
                try {
                    highestScore = Integer.parseInt(data[2]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                Player loadedPlayer = new Player(username, password, highestScore);

                playerList.add(loadedPlayer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return playerList;
    }

    public static void saveUsers(List<Player> playerList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("midterms/OtherActivity/data/Players.txt"))) {
            for (Player player : playerList) {
                StringBuilder s = new StringBuilder();

                s.append(player.getUsername())
                        .append(",")
                        .append(player.getPassword())
                        .append(",")
                        .append(player.getHighestScore());

                writer.write(s.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player signIn(List<Player> playerList) {
        String username;
        while (true) {
            username = InputMethods.inputString("Username: ");
            
            for (Player player : playerList) {
                if (username.equals(player.getUsername())) {
                    
                }
            }
        }
    }

    public static void signUp(List<Player> playerList) {
        System.out.println("Lets sign you up!!!");

    }
}