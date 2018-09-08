package ee.sda.maven.messagingapp;

import java.io.IOException;

public class FileUserService {
    IOUtils ioUtils;
    VerificationUtil verificationUtil;
    PasswordUtils passwordUtils;

    public FileUserService(IOUtils ioUtils) {
        this.ioUtils = ioUtils;
    }


    public void addUser() throws IOException {
        ioUtils.writeMessage("Enter email: ");
        String email = ioUtils.readNextLine();
        // regEx email validation
        if (verificationUtil.isEmailValid(email)) {
            if (ioUtils.fileExist(email + ".txt")) {
                ioUtils.writeMessage("User already exist");
            } else {
                ioUtils.writeMessage("Enter password: ");
                String password = passwordUtils.readPassword("Enter asterix password: ");
                System.out.println("The password entered is: "+password);
                ioUtils.writeMessage("Enter name: ");
                String name = ioUtils.readNextLine();
                ioUtils.writeMessage("Enter your age: ");
                String age = ioUtils.readNextLine();
                ioUtils.readNextLine();
                // write to file
                ioUtils.writeToFile(email, password, name, age);
                ioUtils.writeMessage("A new account with your email has been created");
            }
        } else {
            System.out.println("Invalid email used. Please try again. \n");
        }
        return;
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
            if (ioUtils.readPasswordFromFile(email).equals(password)){
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
