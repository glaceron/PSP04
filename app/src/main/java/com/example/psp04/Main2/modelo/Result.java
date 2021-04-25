package com.example.psp04.Main2.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result
{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("bicisDisponibles")
    @Expose
    private int bicisDisponibles;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("anclajesDisponibles")
    @Expose
    private int anclajes;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;


    public String getTitle()
    {
        return title;
    }
    public int getBicisDisponibles()
    {
        return bicisDisponibles;
    }
    public int getAnclajes()
    {
        return anclajes;
    }
    public String getLastUpdated()
    {
        return lastUpdated;
    }
    public String getEstado()
    {
        return estado;
    }

}