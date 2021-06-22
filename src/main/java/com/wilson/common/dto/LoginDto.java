package com.wilson.common.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * FileName:   vuelog
 * Author:     Ir-verng
 * Date:       2021/6/22   11:39
 * Description:
 */
@Data

public class LoginDto  implements Serializable {
    @NotBlank(message = "昵称不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

}
