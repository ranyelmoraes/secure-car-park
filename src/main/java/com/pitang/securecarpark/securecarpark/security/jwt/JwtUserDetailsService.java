package com.pitang.securecarpark.securecarpark.security.jwt;

import com.pitang.securecarpark.securecarpark.entity.User;
import com.pitang.securecarpark.securecarpark.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findByLogin(login);
        return new JwtUserDetails(user);
    }

    public JwtToken getTokenAuthenticated (String login) {
         return JwtUtils.createToken(login);
    }
}
