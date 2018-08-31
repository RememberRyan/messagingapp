package ee.sda.maven.messagingapp;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class IOUtils {

    Scanner scanner;

    public IOUtils() {
        scanner = new Scanner(System.in);
    }

    public boolean fileExist(Path fileName) {
        return Files.exists(fileName);
    }

    public boolean fileExist(String fileName) {
        Path filePath = Paths.get(fileName);
        return fileExist(filePath);
    }


    public void writeMessage(String message) {
        System.out.println(message);
    }

    public String readNextLine() {
        return scanner.nextLine();
    }
}