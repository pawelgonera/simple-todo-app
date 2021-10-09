package edu.demo.simpletodo.service;


import edu.demo.simpletodo.config.ConfigBeans;
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
        Optional<User> userOptional = Optional.of(NOT_VALID_USER);
        doReturn(userOptional).when(userRepository).findUserByUsername(VALID_USER.getUsername());

        //when
        final Optional<User> userFound = userService.getUserByUsername(VALID_USER.getUsername());

        //then
        assertEquals(userOptional, userFound);

    }
}
