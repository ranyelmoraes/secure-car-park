package com.pitang.securecarpark.securecarpark.utils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsUtil {

    public static UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    public static String getCurrentUsername() {
        UserDetails userDetails = getCurrentUserDetails();
        return userDetails != null ? userDetails.getUsername() : null;
    }
}

