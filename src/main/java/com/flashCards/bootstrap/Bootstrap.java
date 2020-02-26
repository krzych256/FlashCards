package com.flashCards.bootstrap;

import com.flashCards.domain.User;
import com.flashCards.domain.auth.Authority;
import com.flashCards.repositories.AuthorityRepository;
import com.flashCards.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public Bootstrap(PasswordEncoder passwordEncoder,
                     UserRepository userRepository,
                     AuthorityRepository authorityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        User users = new User();
        users.setUsername("user");
        users.setEmail("user@email.com");
        users.setPassword(passwordEncoder.encode("pass"));
        users.setEnabled(true);

        Authority authorities = new Authority();
        authorities.setUsername("user");
        authorities.setAuthority("ROLE_USER");

        userRepository.save(users);
        authorityRepository.save(authorities);
    }
}
