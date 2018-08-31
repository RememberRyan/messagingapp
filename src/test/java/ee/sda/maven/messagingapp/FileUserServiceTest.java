package ee.sda.maven.messagingapp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileUserServiceTest {

    @Mock
    IOUtils ioUtils;

    FileUserService fileUserService;

    @Before
    public void setUp() throws Exception {
        fileUserService = new FileUserService(ioUtils);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addUser_RejectsUserInputEmail_IfUserEmailAlreadyExist() {
        // given
        when(ioUtils.readNextLine()).thenReturn("check");
        when(ioUtils.fileExist(anyString())).thenReturn(true);

        // when
        fileUserService.addUser();

        // then
        verify(ioUtils).writeMessage(eq("Enter email: "));

        verify(ioUtils).writeMessage(eq("User already exist"));
    }

    @Test
    public void addUser_AcceptsUserInputEmail_IfUserEmailDoesNotExist() {
        // given
        when(ioUtils.readNextLine()).thenReturn("check");
        when(ioUtils.fileExist(anyString())).thenReturn(false);

        // when
        fileUserService.addUser();

        // then
        verify(ioUtils).writeMessage(eq("Enter email: "));

        verify(ioUtils).writeMessage(eq("All good"));
    }


//    @Test
//    public void addUser_AcceptsUserInputEmail_IfUserEmailPassesValidation() {
//        // given
//        when(ioUtils.readNextLine()).thenReturn("check");
//        when(ioUtils.fileExist(anyString())).thenReturn(false);
//
//        // when
//        fileUserService.addUser();
//        verify(ioUtils).writeMessage(eq("Enter email: "));
//        ioUtils.scanner.nextLine("hello@gmail.com");
//
//
//        // then
//
//        verify(ioUtils).writeMessage(eq("All good"));
//    }

}