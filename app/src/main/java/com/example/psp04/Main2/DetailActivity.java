package com.example.psp04.Main2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.psp04.R;
import com.example.psp04.databinding.ActivityDetailBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity
{
    private static final String TAG = "DetailActivity";
    private ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        getSupportActionBar().hide();
        View v = binding.getRoot();
        setContentView(v);
        getIncomingIntent();
    }
    private void getIncomingIntent()
    {
        if(getIntent().hasExtra("title") && getIntent().hasExtra("anclajes") && getIntent().hasExtra("bicis") && getIntent().hasExtra("estado") && getIntent().hasExtra("date"))
        {
            String titulo = getIntent().getStringExtra("title");
            int anclajes = getIntent().getIntExtra("anclajes", 0);
            int bicis = getIntent().getIntExtra("bicis", 0);
            String estado = getIntent().getStringExtra("estado");
            String date = getIntent().getStringExtra("date");
            setDetailData(titulo,estado, date, anclajes, bicis);
        }
    }

    private void setDetailData(String titulo, String estado, String date, int anclajes, int bicis)
    {
        binding.textViewHuecos.setText(String.valueOf(anclajes));
        binding.textViewBicisDisponibles.setText(String.valueOf(bicis));
        binding.textViewNombre.setText(titulo);
        if (estado.equalsIgnoreCase("OPN"))
        {
            binding.textViewEstadoParking.setText("Disponible");
        }
        else
        {
            binding.textViewEstadoParking.setText("No disponible");
        }
        binding.textViewUltimaActualizacion.setText(dateFormat(date).toString());
    }


    private Date dateFormat(String date)
    {
        Date Fecha = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try
        {
            Fecha = format.parse(date);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return Fecha;
    }
}