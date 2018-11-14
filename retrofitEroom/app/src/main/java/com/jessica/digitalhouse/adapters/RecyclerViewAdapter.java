package com.jessica.digitalhouse.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.jessica.digitalhouse.R;
import com.jessica.digitalhouse.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Result> results;

    public RecyclerViewAdapter(List<Result> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Result result = results.get(i);
        viewHolder.bind(result);
    }

    //método que atualiza a lista do adapter
    public void setResult(List<Result> results) {
        //verificar se o result já tem informação
        if (results.size() == 0) {
            this.results = results;
        } else {
            this.results.addAll(results);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFilme;
        private TextView txtTituloFilme;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFilme = itemView.findViewById(R.id.imgFilme);
            txtTituloFilme = itemView.findViewById(R.id.txtTitulo);
        }

        public void bind(Result result){
            txtTituloFilme.setText(result.getTitle().toString());

            //Pegando a imagem de poster do filme através do Picasso
            Picasso.get().load("http://image.tmdb.org/t/p/w500/"+result.getPosterPath()).into(imgFilme);
        }
    }
}
