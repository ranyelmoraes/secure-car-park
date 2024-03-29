package com.pitang.securecarpark.securecarpark.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class JwtUserDetails extends User {
    private com.pitang.securecarpark.securecarpark.entity.User user;
    public JwtUserDetails(com.pitang.securecarpark.securecarpark.entity.User user) {
        super(user.getLogin(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }

    public Long getId () {
        return this.user.getId();
    }
}
