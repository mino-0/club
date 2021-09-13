package com.mino.club.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode() {
        String password = "1111";
        String enPw = passwordEncoder.encode(password);

        System.out.println("enPw : " + enPw);

        boolean matchResult1 = passwordEncoder.matches(password, enPw);
        System.out.println("matchResult1 = " + matchResult1);

        boolean matchResult2 = passwordEncoder.matches("1111", enPw);
        System.out.println("matchResult2 = " + matchResult2);
    }
}
