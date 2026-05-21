package org.my.project.security;
import org.my.project.entity.User;
import org.my.project.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
private final UserRepository userrepository;

    public CustomUserDetailsService(UserRepository userrepository) {
        this.userrepository = userrepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =userrepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

return org.springframework.security.core.userdetails.User.builder()
        .username(user.getUserName())
            .password(user.getPassword())
            .roles(user.getRoles().name())
            .build();
    }





}
