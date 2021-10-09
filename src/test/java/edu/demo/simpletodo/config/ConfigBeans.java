package edu.demo.simpletodo.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import edu.demo.simpletodo.api.UserService;
import edu.demo.simpletodo.repository.TaskRepository;
import edu.demo.simpletodo.repository.UserRepository;
import edu.demo.simpletodo.repository.UserRoleRepository;

@Configuration
public class ConfigBeans {

    @Bean
    public UserRepository userRepository(){
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public TaskRepository taskRepository(){
        return Mockito.mock(TaskRepository.class);
    }

    @Bean
    public UserRoleRepository userRoleRepository(){
        return Mockito.mock(UserRoleRepository.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return Mockito.mock(PasswordEncoder.class);
    }

}
