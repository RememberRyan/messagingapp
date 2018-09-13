package ee.sda.maven.messagingapp;
import ee.sda.maven.messagingapp.service.FileUserService;
import ee.sda.maven.messagingapp.validation.VerificationUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileUserServiceTest {

    @Mock
    IOUtils ioUtils;
    VerificationUtil verificationUtil;

    FileUserService fileUserService;

    @Before
    public void setUp() throws Exception {
        /*
        Stopped working ????
        fileUserService = new FileUserService(ioUtils);
         */
        fileUserService = new FileUserService();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addUser_RejectsUserInputEmail_IfUserEmailAlreadyExist() throws IOException {
        // given
        when(ioUtils.readNextLine()).thenReturn("krislinjurgen@gmail.com");
        when(ioUtils.fileExist(anyString())).thenReturn(true);

        // when
        fileUserService.addUser();
        verify(ioUtils).writeMessage(eq("Enter email: "));

        // then

        verify(ioUtils).writeMessage(eq("User already exist"));
    }

    @Test
    public void addUser_RejectsUserInputEmail_IfUserEmailDoesNotExistAndFailsVerification() throws IOException {
        // given
        /*
        Not necessary because email user input is rejected whether the file exists or not
        when(ioUtils.fileExist(anyString())).thenReturn(false);
         */
        when(ioUtils.readNextLine()).thenReturn("@ BrandNewValidEmail@gmail.com");

        // when
        fileUserService.addUser();
        verify(ioUtils).writeMessage(eq("Enter email: "));

        // then
        ioUtils.writeMessage("Invalid email used. Please try again. \n");
    }


    @Test
    public void addUser_AcceptsUserInputEmail_IfUserEmailDoesNotExistAndPassesVerification() throws IOException {
        // given
        when(ioUtils.readNextLine()).thenReturn("BrandNewValidEmail@gmail.com");
        when(ioUtils.fileExist(anyString())).thenReturn(false);

        // when
        fileUserService.addUser();
        verify(ioUtils).writeMessage(eq("Enter email: "));


        // then
        verify(ioUtils).writeMessage(eq("A new account with your email has been created"));
    }

    @Test
    public void login_AcceptsUserPassword_IfUserEmailsPasswordMatches() throws IOException {
        // given
        // given the file  already exists (true)
        when(ioUtils.fileExist("krislinjurgen@gmail.com.txt")).thenReturn(true);
        // user inputs the email and then incorrect password
        when(ioUtils.readNextLine()).thenReturn("krislinjurgen@gmail.com").thenReturn("correctpassword");
        // the app returns correct password
        when(ioUtils.readPasswordFromFile("krislinjurgen@gmail.com")).thenReturn("correctpassword");

        // when
        fileUserService.login();
        verify(ioUtils).writeMessage(eq("Enter your login email: "));
        verify(ioUtils).writeMessage(eq("Enter your password: "));

        // then
        verify(ioUtils).writeMessage(eq("You have successfully logged in. \nChoose another option.\n"));
    }

    @Test
    public void login_RejectsUserPassword_IfUserEmailsPasswordDoesNotMatch() throws IOException {
        // given
        // given the file  already exists (true)
        when(ioUtils.fileExist("krislinjurgen@gmail.com.txt")).thenReturn(true);
        // user inputs the email and then incorrect password
        when(ioUtils.readNextLine()).thenReturn("krislinjurgen@gmail.com").thenReturn("notthepassword");
        // the app returns correct password
        when(ioUtils.readPasswordFromFile("krislinjurgen@gmail.com")).thenReturn("correctpassword");

        // when
        fileUserService.login();
        verify(ioUtils).writeMessage(eq("Enter your login email: "));
        verify(ioUtils).writeMessage(eq("Enter your password: "));

        // then
        verify(ioUtils).writeMessage(eq("Your login details are incorrect.\nPlease choose another option.\n"));
    }

    @Test
    public void login_RejectsUserPassword_IfUserEmailsPasswordDoesNotMatchCaseSensitivity() throws IOException {
        // given
        // given the file  already exists (true)
        when(ioUtils.fileExist("krislinjurgen@gmail.com.txt")).thenReturn(true);
        // user inputs the email and then incorrect password
        when(ioUtils.readNextLine()).thenReturn("krislinjurgen@gmail.com").thenReturn("Correctpassword");
        // the app returns correct password
        when(ioUtils.readPasswordFromFile("krislinjurgen@gmail.com")).thenReturn("correctpassword");

        // when
        fileUserService.login();
        verify(ioUtils).writeMessage(eq("Enter your login email: "));
        verify(ioUtils).writeMessage(eq("Enter your password: "));

        // then
        verify(ioUtils).writeMessage(eq("Your login details are incorrect.\nPlease choose another option.\n"));
    }

}