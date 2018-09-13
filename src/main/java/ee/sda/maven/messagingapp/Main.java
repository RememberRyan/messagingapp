package ee.sda.maven.messagingapp;

import ee.sda.maven.messagingapp.service.FileUserService;
import ee.sda.maven.messagingapp.user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        IOUtils ioUtils = new IOUtils();
        Scanner scanner = new Scanner(System.in);
        User user;
        Path pathToChat = Paths.get("./chats/chat.txt");
        boolean repeatLoginMenu = true;
        FileUserService fileUserService = new FileUserService();
        while (repeatLoginMenu) {
            System.out.println("1. Sign up");
            System.out.println("2. Log in");
            System.out.println("3. Create Message");
            System.out.println("0. Exit");
            int answer = Integer.parseInt(scanner.nextLine());
            switch (answer) {
                case 1:
                    user = fileUserService.addUser();
                    // read all lines from chat.txt and print them to console
                    Files.readAllLines(Paths.get("./chats/chat.txt"));
                    List<String> oldMessageHistory = Files.readAllLines(pathToChat);
                    for (int i = 0; i < oldMessageHistory.size(); i++) {
                        System.out.println(oldMessageHistory.get(i));
                    }
                    repeatLoginMenu = false;

                    System.out.println("You have successfully logged in. \nPlease write your chat message below:\n");
                    String newChatMessage = ioUtils.readNextLine();
                    ioUtils.writeNewMessageToChatFile(user.getEmail(), newChatMessage);

                    break;
                case 2:
                    fileUserService.login();
                    repeatLoginMenu = false;
                    break;
                case 0:
                    return;

            }
        }
    }
}