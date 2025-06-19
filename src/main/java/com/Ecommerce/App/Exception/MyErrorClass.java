package com.Ecommerce.App.Exception;

import java.time.LocalDateTime;

public class MyErrorClass {
    private String message;
    private LocalDateTime localDateTimes;
    private String desc;

    public MyErrorClass() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTimes() {
        return localDateTimes;
    }

    public void setLocalDateTimes(LocalDateTime localDateTimes) {
        this.localDateTimes = localDateTimes;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
