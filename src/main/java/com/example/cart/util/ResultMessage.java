package com.example.cart.util;

import org.springframework.stereotype.Component;

/**
 * @author: wsc
 * @createTime: 2024/04/12 20:58
 * @company:
 * @description:
 */

@Component
public class ResultMessage {
    private String code;
    private String msg;
    private Object data;

    public void success(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public void success(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public void success(String code, Object data) {
        this.code = code;
        this.msg = null;
        this.data = data;
    }

    public void fail(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
}
