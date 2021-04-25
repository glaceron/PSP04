package com.example.psp04.Main1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.psp04.R;
import com.example.psp04.databinding.ActivityMain1Binding;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main1 extends AppCompatActivity implements View.OnClickListener
{
    private ActivityMain1Binding binding;
    private String myResponse;
    private double ratio;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonConvertir.setOnClickListener(this);
        binding.radioButtonDaE.setOnClickListener(this);
        binding.radioButtonEaD.setOnClickListener(this);

        binding.radioButtonEaD.setChecked(true);
        binding.editTextDolares.setEnabled(false);

        OkHttpClient client = new OkHttpClient();

        String url = "http://api.exchangeratesapi.io/v1/latest?access_key=3282a938c8cfbdfd1a4a491d7b5b3508&symbols=USD";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                if (response.isSuccessful())
                {
                    myResponse = response.body().string();
                    Main1.this.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Gson gson = new Gson();
                            Respuesta respuesta = (Respuesta) gson.fromJson(myResponse, Respuesta.class);
                            ratio = respuesta.getRate().getUsd();
                        }
                    });
                }
            }
        });
    }


    @Override
    public void onClick(View view)
    {
        if(view == binding.buttonConvertir)
        {
            if(binding.radioButtonDaE.isChecked())
            {
                try
                {
                    binding.editTextEuros.setText(String.valueOf(Double.parseDouble(String.valueOf(binding.editTextDolares.getText())) / ratio));
                }
                catch (NumberFormatException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Introduce un numero de Dolares valido.",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
            else
            {
                try {
                    binding.editTextDolares.setText(String.valueOf(Double.parseDouble(String.valueOf(binding.editTextEuros.getText())) * ratio));
                }
                catch (NumberFormatException e)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Introduce un numero de Euros valido.",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }
        if(view == binding.radioButtonDaE)
        {
            binding.editTextEuros.setEnabled(false);
            binding.editTextDolares.setEnabled(true);
        }
        if(view == binding.radioButtonEaD)
        {
            binding.editTextEuros.setEnabled(true);
            binding.editTextDolares.setEnabled(false);
        }
    }
}