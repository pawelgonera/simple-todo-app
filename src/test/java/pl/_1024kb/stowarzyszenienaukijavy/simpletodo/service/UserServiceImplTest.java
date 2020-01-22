package pl._1024kb.stowarzyszenienaukijavy.simpletodo.service;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.UserService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.config.ConfigBeans;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.TaskRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = UserServiceImpl.class)
@Import(ConfigBeans.class)
public class UserServiceImplTest
{
    private static final User VALID_USER = User.builder().username("Poul").password("password").email("poul@g.com").build();
    private static final User NOT_VALID_USER = new User(1L, "P", "mail.com", "pass", "pase");

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    @Ignore
    public void shouldReturnUserByUsername()
    {
        //given
        Optional<User> userOptional = Optional.of(VALID_USER);
        doReturn(userOptional).when(userRepository).findUserByUsername(VALID_USER.getUsername());

        //when
        final Optional<User> userFound = userService.getUserByUsername(VALID_USER.getUsername());

        //then
        assertEquals(userOptional, userFound);

    }
}
