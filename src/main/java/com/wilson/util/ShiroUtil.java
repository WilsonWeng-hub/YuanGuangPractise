package com.wilson.util;

import com.wilson.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * FileName:   vuelog
 * Author:     Ir-verng
 * Date:       2021/6/23   12:52
 * Description:
 */
public class ShiroUtil {
    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}
