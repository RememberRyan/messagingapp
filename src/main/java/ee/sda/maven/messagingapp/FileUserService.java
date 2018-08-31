package ee.sda.maven.messagingapp;
public class FileUserService {
    IOUtils ioUtils;

    public FileUserService(IOUtils ioUtils) {
        this.ioUtils = ioUtils;
    }


    public void addUser(){
        //try{
        ioUtils.writeMessage("Enter email: ");
        String email = ioUtils.readNextLine();
        if (ioUtils.fileExist(email + ".txt")) {
            ioUtils.writeMessage("User already exist");
            return;
        }
        ioUtils.writeMessage("All good");

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
