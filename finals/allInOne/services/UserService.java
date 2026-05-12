package finals.allInOne.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import finals.allInOne.models.User;
import finals.allInOne.InputMethods;

public class UserService {
    private static final String USER_FILE_PATH = "finals/theact/data/Users.txt";

    public static List<User> loadUsers() {
        List<User> userList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length < 3) {
                    InputMethods.println(String.format("Skipping malformed line: %s", line));
                    continue;
                }

                String username = data[0];
                String password = data[1];
                int highestScore = 0;
                try {
                    highestScore = Integer.parseInt(data[2]);
                } catch (NumberFormatException e) {
                    InputMethods.println("Skipping line with invalid score: " + line);
                    continue;
                }

                User loadedUser = new User(username, password, highestScore);

                userList.add(loadedUser);
            }
        } catch (FileNotFoundException e) {
            InputMethods.println("File not found. Starting Fresh!");
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static void saveUsers(List<User> userList) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE_PATH))) {
            StringBuilder s = new StringBuilder();

            for (User user : userList) {
                s.setLength(0);

                s.append(user.getUsername())
                        .append(",")
                        .append(user.getPassword())
                        .append(",")
                        .append(user.getHighestScore());

                writer.write(s.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User login(List<User> userList, Scanner input) {
        String username = InputMethods.inputString("username: ", input);
        String password = InputMethods.inputString("password: ", input);

        for (User user : userList) {
            if (user.getPassword().equals(password) && user.getUsername().equals(username)) {
                return user;
            }
        }

        InputMethods.println("Invalid credentials!");
        return null;
    }

    public static void register(List<User> userList, Scanner input) {
        String username = "";
        while (true) {
            username = InputMethods.inputString("username: ", input);

            boolean usernameExists = false;
            for (User user : userList) {
                if (username.equals(user.getUsername())) {
                    usernameExists = true;
                    break;
                }
            }

            if (usernameExists) {
                InputMethods.println("Username already exists!");
            } else {
                break;
            }
        }

        String password = InputMethods.inputString("password: ", input);

        User user = new User(username, password, 0);

        userList.add(user);

        saveUsers(userList);
    }

    public static void displayLeaderboard(List<User> userList) {
        InputMethods.println("""
                 ____________________________________________________
                |                                                    |
                |                   LEADERBOARD                     |
                |____________________________________________________|
                """);

        if (userList.isEmpty()) {
            InputMethods.println("No players registered yet!");
            return;
        }

        List<User> sorted = new ArrayList<>(userList);
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - 1 - i; j++) {
                if (sorted.get(j).getHighestScore() < sorted.get(j + 1).getHighestScore()) {
                    User temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }

        String[] medals = { "🥇", "🥈", "🥉" };

        InputMethods.println(String.format("%-6s %-20s %s", "Rank", "Username", "Highest Score"));
        InputMethods.println("----------------------------------------------");

        for (int i = 0; i < sorted.size(); i++) {
            User user = sorted.get(i);
            String rank;
            if (i < 3) {
                rank = medals[i] + " #" + (i + 1);
            } else {
                rank = "   #" + (i + 1);
            }
            InputMethods.println(String.format("%-9s %-20s %d", rank, user.getUsername(), user.getHighestScore()));
        }

        InputMethods.println("----------------------------------------------");
    }
}
