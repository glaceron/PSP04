package com.example.psp04.Main2;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psp04.Main2.modelo.Result;
import com.example.psp04.databinding.ItemViewBinding;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {
    public ArrayList<Result> mResult;

    public ResultAdapter(){
        this.mResult = new ArrayList<>();
    }

    public void setParkings(ArrayList<Result> result) {
        mResult = result;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemViewBinding binding;

        public MyViewHolder(ItemViewBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

    @Override
    public int getItemCount() {
        return mResult.size();
    }

    @NonNull
    @Override
    public ResultAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.MyViewHolder holder, int position) {

        Result result = mResult.get(position);
        holder.binding.textViewNombreEstacion2.setText(result.getTitle());
        holder.binding.textViewPlazasLibres.setText(String.valueOf(result.getAnclajes()));
    }


}
