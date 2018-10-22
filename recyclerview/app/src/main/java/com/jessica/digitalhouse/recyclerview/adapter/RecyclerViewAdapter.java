package com.jessica.digitalhouse.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jessica.digitalhouse.recyclerview.R;
import com.jessica.digitalhouse.recyclerview.model.Pessoa;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Pessoa> pessoas;

    public RecyclerViewAdapter(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    //método que passa a "pessoa" para o método bind()
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pessoa pessoa = pessoas.get(position);
        holder.bind(pessoa);
    }

    //Retorna o tamanho da lista
    @Override
    public int getItemCount() {
        return pessoas.size();
    }

    // criar ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
       private TextView txtNome;
       private TextView txtIdade;

        public ViewHolder(View itemView) {
           super(itemView);

           txtNome = itemView.findViewById(R.id.txtNome);
           txtIdade = itemView.findViewById(R.id.txtIdade);
       }

       //método bind
       public void bind(Pessoa pessoa){
            txtNome.setText(pessoa.getNome());
            txtIdade.setText(pessoa.getIdade());
       }
   }
}
