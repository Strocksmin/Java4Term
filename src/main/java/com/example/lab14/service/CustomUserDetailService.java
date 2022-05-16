package com.example.lab14.service;

import com.example.lab14.data.CustomUser;
import com.example.lab14.model.User;
import com.example.lab14.model.UserGroup;
import com.example.lab14.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public CustomUser loadUserByUsername(String email) throws UsernameNotFoundException {
        final User userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return CustomUser.CustomUserBuilder.aCustomUser().withUsername(userEntity.getEmail())
                .withPassword(userEntity.getPassword())
                .withAuthorities(getAuthorities(userEntity)).build();
    }

    private Collection<GrantedAuthority> getAuthorities(User user){
        Set<UserGroup> userGroups = user.getUserGroups();
        Collection<GrantedAuthority> authorities = new ArrayList<>(userGroups.size());
        for(UserGroup userGroup : userGroups){
            authorities.add(new SimpleGrantedAuthority(userGroup.getCode().toUpperCase()));
        }

        return authorities;
    }
}
