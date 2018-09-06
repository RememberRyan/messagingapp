package ee.sda.maven.messagingapp;
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
        fileUserService = new FileUserService(ioUtils);
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
        when(ioUtils.readNextLine()).thenReturn("@ BrandNewValidEmail@gmail.com");
        when(ioUtils.fileExist(anyString())).thenReturn(false);

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

    // incomplete
    @Test
    public void login_RejectsUserPassword_IfUserEmailsPasswordDoesNotMatch() throws IOException {
        // given
        when(ioUtils.readNextLine()).thenReturn("notthepassword");
        when(ioUtils.readPasswordFromFile("krislinjurgen@gmail.com")).thenReturn("pa55w0rd");

        // when
        fileUserService.login();


        // then
        verify(ioUtils.writeMessage("Enter your login email: ");
        verify(ioUtils).writeMessage(eq("Enter your password: "));
        verify(ioUtils).writeMessage(eq("Your login details are incorrect.\nPlease choose another option.\n"));
    }


}