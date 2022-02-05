package com.soa.proyecto;

import org.springframework.security.crypto.password.PasswordEncoder;

public class NoEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        System.out.println(rawPassword);
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println(rawPassword);
        System.out.println(encodedPassword);
        return rawPassword.toString().equals(encodedPassword);
    }
}
