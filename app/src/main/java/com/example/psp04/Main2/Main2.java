package com.example.psp04.Main2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.psp04.Main2.modelo.Result;
import com.example.psp04.Main2.modelo.Lista;
import com.example.psp04.R;
import com.example.psp04.databinding.ActivityMain2Binding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2 extends AppCompatActivity implements Callback<Lista>
{
    private ActivityMain2Binding binding;
    private ResultAdapter adapter;
    private Call<Lista> call;
    private ArrayList<Result> resultados;
    private List<Lista> estaciones;
    private SwipeRefreshLayout swipeRefreshLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        getSupportActionBar().setTitle("Bizi");

        context = getApplicationContext();

        adapter = new ResultAdapter();
        binding.recyclerView.setAdapter(adapter);
        swipeRefreshLayout=binding.swipeRefreshLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.design_default_color_primary);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.material_on_surface_emphasis_medium);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        populateList();

        binding.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, binding.recyclerView, new ClickListener()
        {
            @Override
            public void onClick(View view, final int position)
            {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("title", resultados.get(position).getTitle());
                intent.putExtra("anclajes", resultados.get(position).getAnclajes());
                intent.putExtra("bicis", resultados.get(position).getBicisDisponibles());
                intent.putExtra("estado", resultados.get(position).getEstado());
                intent.putExtra("date", resultados.get(position).getLastUpdated());
                startActivity(intent);
            }
        }));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                populateList();
            }
        });
    }

    public void populateList()
    {
        call = AdaptadorApi.getInstance().getResults();
        call.enqueue(this);
    }

    private void showMessage(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Call<Lista> call, Response<Lista> response)
    {
        if(response.isSuccessful())
        {
            estaciones = Collections.singletonList(response.body());
            for( Lista e : estaciones)
            {
                resultados = e.getResult();
                adapter.setParkings(resultados);
                binding.recyclerView.setAdapter(adapter);
            }
            showMessage("Parkings actualizados");
            swipeRefreshLayout.setRefreshing(false);
        }
        else
            {
            StringBuilder message = new StringBuilder();
            message.append("Error en la descarga " + response.code());
            if(response.body() != null)
            {
                message.append("\n" + response.body());
            }
            if(response.errorBody() != null)
            {
                try
                {
                    message.append("\n" + response.errorBody().string());
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
                showMessage(message.toString());
            }
        }
    }
    @Override
    public void onFailure(Call<Lista> call, Throwable t)
    {

    }
}