package ee.sda.maven.messagingapp;

import java.io.IOException;

public class FileUserService {
    IOUtils ioUtils;

    public FileUserService(IOUtils ioUtils) {
        this.ioUtils = ioUtils;
    }


    public void addUser() throws IOException {
        //try{
        ioUtils.writeMessage("Enter email: ");
        String email = ioUtils.readNextLine();
        // should I put regEx email validation here???

        // account uniqueness and account creation
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
            return;
        }

//            System.out.println("Enter your password");
//            String password = scanner.nextLine();
//            System.out.println("Enter your full name: ");
//            String name = scanner.nextLine();
//            System.out.println("Enter your age: ");
//            int age = scanner.nextInt();
//            PrintWriter writer = new PrintWriter(email + ".txt");
//            writer.println(email);
//            writer.println(name);
//            writer.println(age);
//            writer.println(password);
//            writer.close();
//        }catch (IOException e){
//            System.out.println("file not found");
//        }
    }
}
