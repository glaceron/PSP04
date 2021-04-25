package com.example.psp04.Main2.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Lista
{

    @SerializedName("result")
    @Expose
    private ArrayList<Result> result = null;

    public Lista(ArrayList<Result> result)
    {
        super();
        this.result = result;
    }
    public ArrayList<Result> getResult()
    {
        return result;
    }

}
