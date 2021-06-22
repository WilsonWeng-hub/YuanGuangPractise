package com.wilson.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * FileName:   vuelog
 * Author:     Ir-verng
 * Date:       2021/6/19   15:57
 * Description:Return data & msg & status
 */
@Data
public class Result implements Serializable {
    private int code;
    private Object data;
    private String msg;

    public static Result succ(Object data){
        return succ(200,"操作成功",data);
    }
    public static Result succ(int code, String msg, Object data){
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }


    public static Result fail(String msg){
        return fail(msg,null);
    }
    public static Result fail(String msg,Object data){
        return fail(400, msg,null);
    }
    public static Result fail(int code, String msg, Object data){
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

}
