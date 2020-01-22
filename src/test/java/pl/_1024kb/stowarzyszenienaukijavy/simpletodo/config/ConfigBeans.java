package pl._1024kb.stowarzyszenienaukijavy.simpletodo.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.UserService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.TaskRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.UserRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.UserRoleRepository;

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
    public UserService userService(){
        return Mockito.mock(UserService.class);
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
