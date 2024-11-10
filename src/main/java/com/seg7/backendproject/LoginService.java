package com.seg7.backendproject;

import org.springframework.stereotype.Service;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service
public class LoginService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        User dbUser = null;
        // 資料庫搜尋此郵件是否存在
        if (dbUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                dbUser.getEmail(),
                dbUser.getPassword(),
                Collections.emptyList());
    }

}
