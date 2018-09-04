package ee.sda.maven.messagingapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

// needed to read in user details as an array for writeToFile method
import static java.util.Arrays.asList;


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

    public void readPasswordFromFile(String email, String password) {
        int passwordLineNumber = 2;
        try {
            String str = Files.lines(Paths.get("/Users/Alex/IdeaProjects/messagingapp/" + email + ".txt")).skip(passwordLineNumber - 1).findFirst().get();
            System.out.println("Content at " + passwordLineNumber + " Number:- " + str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // email validation with regEx
    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

}