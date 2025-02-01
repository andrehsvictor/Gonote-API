package andrehsvictor.gonote.security.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import andrehsvictor.gonote.exception.ResourceNotFoundException;
import andrehsvictor.gonote.user.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return new UserDetailsImpl(userService.getByEmail(username));
        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

}
