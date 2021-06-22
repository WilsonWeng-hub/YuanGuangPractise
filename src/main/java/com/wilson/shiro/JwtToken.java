package com.wilson.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * FileName:   vuelog
 * Author:     Ir-verng
 * Date:       2021/6/19   17:20
 * Description:
 */
public class JwtToken implements AuthenticationToken {
    private  String token;

    public JwtToken(String jwt) {
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
