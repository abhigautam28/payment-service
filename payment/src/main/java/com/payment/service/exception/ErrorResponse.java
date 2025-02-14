package com.payment.service.exception;

import lombok.Data;

import java.util.Date;

public class ErrorResponse {
    private Date timeLine;
    private int status;
    private String msg;

    public ErrorResponse(Date timeLine, int status, String msg) {
        this.timeLine = timeLine;
        this.status = status;
        this.msg = msg;
    }
}
