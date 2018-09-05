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

        // then
        verify(ioUtils).writeMessage(eq("Enter email: "));

        verify(ioUtils).writeMessage(eq("User already exist"));
    }

    @Test
    public void addUser_AcceptsUserInputEmail_IfUserEmailDoesNotExist() throws IOException {
        // given
        when(ioUtils.readNextLine()).thenReturn("check");
        when(ioUtils.fileExist(anyString())).thenReturn(false);

        // when
        fileUserService.addUser();

        // then
        verify(ioUtils).writeMessage(eq("Enter email: "));

        verify(ioUtils).writeMessage(eq("All good"));
    }


    // incomplete test
    @Test
    public void addUser_AcceptsUserInputEmail_IfUserEmailPassesValidation() throws IOException {
        // given
        when(ioUtils.readNextLine()).thenReturn("Jurgen@gmail.com");
        when(verificationUtil.isEmailValid(anyString())).thenReturn(false);

        // when
        fileUserService.addUser();
        verify(ioUtils).writeMessage(eq("Enter email: "));


        // then

        verify(ioUtils).writeMessage(eq("All good"));
    }

}