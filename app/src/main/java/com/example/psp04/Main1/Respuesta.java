package com.example.psp04.Main1;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;

public class Respuesta implements Serializable
{
    private boolean succes;
    private Long timestamp;
    private String base,date;
    private Rate rates;

    public boolean isSucces() {
        return succes;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Rate getRate() {
        return rates;
    }
}
