package org.hhh.system.custom;

import org.hhh.common.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//自定义密码组件
@Component
public class CustomMd5Password implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        //return MD5.encrypt(rawPassword.toString());
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
        return encodedPassword.equals(rawPassword.toString());
    }
}
