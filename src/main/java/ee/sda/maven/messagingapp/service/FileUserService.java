package ee.sda.maven.messagingapp.service;

import ee.sda.maven.messagingapp.IOUtils;
import ee.sda.maven.messagingapp.user.User;
import ee.sda.maven.messagingapp.validation.VerificationUtil;

import java.io.IOException;

public class FileUserService {
    IOUtils ioUtils = new IOUtils();
    VerificationUtil verificationUtil;

    public FileUserService() {

    }


    public User addUser() throws IOException {
        ioUtils.writeMessage("Enter email: ");
        String email = ioUtils.readNextLine();
        String name = "";
        String age = "";
        String password ="";
        // regEx email validation
        if (verificationUtil.isEmailValid(email)) {
            if (ioUtils.fileExist(email + ".txt")) {
                ioUtils.writeMessage("User already exist");
            } else {
                ioUtils.writeMessage("Enter password: ");
                password = ioUtils.readNextLine();
                ioUtils.writeMessage("Enter name: ");
                name = ioUtils.readNextLine();
                ioUtils.writeMessage("Enter your age: ");
                age = ioUtils.readNextLine();
                ioUtils.readNextLine();
                // write to file
                ioUtils.writeNewUserToFile(email, password, name, age);
                ioUtils.writeMessage("A new account with your email has been created");
            }
        } else {
            System.out.println("Invalid email used. Please try again. \n");
        }
        // created object user
        return new User(email, name, Integer.parseInt(age), password);
    }

    // login functionality
    public void login() throws IOException {
        // request user email login
        ioUtils.writeMessage("Enter your login email: ");
        String email = ioUtils.readNextLine();
        if (ioUtils.fileExist(email + ".txt")) {

            // text file reading
            ioUtils.writeMessage("Enter your password: ");
            String password = ioUtils.readNextLine();
            if (ioUtils.readPasswordFromFile(email).equals(password)) {
                ioUtils.writeMessage("You have successfully logged in. \nChoose another option.\n");
            } else {
                ioUtils.writeMessage("Your login details are incorrect.\nPlease choose another option.\n");
            }

        } else {
            ioUtils.writeMessage("This account does not exist \n");
        }

        return;
    }

}
