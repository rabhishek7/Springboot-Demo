package com.springboot.demo.service;

import com.springboot.demo.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import com.springboot.demo.repository.UserRepository;
import com.springboot.demo.utils.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    private UserType userType;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(userType);

            UserEntity userEntity = userRepository.findByEmail(username);
            if (userEntity == null)
                throw new UsernameNotFoundException(" Username "+ username+ "not found");
            if(userEntity.getStatus()== 1){
                SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(UserType.ADMIN.toString());
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(adminAuthority);
                return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
            } else if (userEntity.getStatus() == 2) {
                SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(UserType.USER.toString());
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(adminAuthority);
                return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
            }

        return null;
    }
}
