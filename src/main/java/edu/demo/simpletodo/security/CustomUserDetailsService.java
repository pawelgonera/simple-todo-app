package edu.demo.simpletodo.security;

import edu.demo.simpletodo.exception.NotFoundDesiredDataRuntimeException;
import edu.demo.simpletodo.model.User;
import edu.demo.simpletodo.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import edu.demo.simpletodo.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService
{
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findUserByUsername(username).orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);

        return new org.springframework.security.core.userdetails.User(
                                    user.getUsername(),
                                    user.getPassword(),
                                    convertAuthorities(user.getRoles())
                                    );
    }

    private Set<GrantedAuthority> convertAuthorities(Set<UserRole> userRoles)
    {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(UserRole role : userRoles)
        {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }
}
