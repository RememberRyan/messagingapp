package ee.sda.maven.messagingapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.util.Arrays.asList;

// needed to read in user details as an array for writeToFile method


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

    public void writeToFile(String email, String password, String name, String age) throws IOException {
        Files.write(Paths.get("/Users/Alex/IdeaProjects/messagingapp/" + email + ".txt" ),
                // reads in user details as an array
                asList(email, password, name, age));
    }

    // return the password from correct line number that has the password from the entered email.txt file
    public String readPasswordFromFile(String email) throws IOException {
          return   Files.readAllLines(Paths.get("/Users/Alex/IdeaProjects/messagingapp/"  + email + ".txt")).get(1);
    }



}