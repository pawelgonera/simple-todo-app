package edu.demo.simpletodo.service;


import edu.demo.simpletodo.config.ConfigBeans;
import edu.demo.simpletodo.exception.UsernameIsAlreadyExistException;
import edu.demo.simpletodo.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import edu.demo.simpletodo.api.UserService;
import edu.demo.simpletodo.repository.TaskRepository;
import edu.demo.simpletodo.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@Import(ConfigBeans.class)
@SpringBootTest(classes = UserServiceImpl.class)
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
    public void shouldReturnUserByUsername()
    {
        //given
        Optional<User> userOptional = Optional.of(VALID_USER);
        doReturn(userOptional).when(userRepository).findUserByUsername(VALID_USER.getUsername());

        //when
        final Optional<User> userFound = userService.getUserByUsername(VALID_USER.getUsername());

        //then
        Optional<User> expected = Optional.of(VALID_USER);
        assertEquals(expected, userFound);
    }

    @Test
    public void shouldNotCreateADuplicatedUser() throws Exception {
        //given
        Optional<User> userOptional = Optional.of(VALID_USER);
        doReturn(userOptional).when(userRepository).findUserByUsername(VALID_USER.getUsername());
        doReturn(true).when(userRepository).existsUserByUsername(VALID_USER.getUsername());

        //when
        assertThrows(Exception.class, ()->userService.createUser(VALID_USER));
    }


}
