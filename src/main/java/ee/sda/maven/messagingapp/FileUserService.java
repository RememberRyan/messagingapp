package ee.sda.maven.messagingapp;

import java.io.IOException;

public class FileUserService {
    IOUtils ioUtils;
    VerificationUtil verificationUtil;

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
                String password = ioUtils.readNextLine();
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
    }

    // login functionality
//    public void login() {
//        // request user email login
//        ioUtils.writeMessage("Enter your login email: ");
//        String email = ioUtils.readNextLine();
//        if (ioUtils.fileExist(email + ".txt")) {
//            if ioUtils.readPasswordFromFile(password) = email;
//
//        } else {
//            ioUtils.writeMessage("This account does not exist");
//        }
//
//        // verify email exists
//
//        // request user email password
//
//        // read and match the second line of that file to user input
//
//        // if password match, prompt user login successful
//
//        // if password fail, prompt user password is not a match
//        return;
//    }


}
