package com.wilson.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * FileName:   vuelog
 * Author:     Ir-verng
 * Date:       2021/6/21   8:13
 * Description:
 */

@Data
public class AccountProfile implements Serializable {
    private Long id;

    private String username;

    private String avatar;

    private String email;

}
