package com.jessica.digitalhouse.agendaroom.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jessica.digitalhouse.agendaroom.R;
import com.jessica.digitalhouse.agendaroom.interfaces.RecyclerViewOnClick;
import com.jessica.digitalhouse.agendaroom.model.Agenda;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHoleder> {

    private List<Agenda> agendaList;
    private RecyclerViewOnClick listener;

    public RecyclerViewAdapter(List<Agenda> agendaList, RecyclerViewOnClick listener) {
        this.agendaList = agendaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, viewGroup, false);
        return new ViewHoleder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoleder viewHoleder, int i) {
        final Agenda agenda = agendaList.get(i);
        viewHoleder.bind(agenda);

        viewHoleder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(agenda);
            }
        });
    }

    @Override
    public int getItemCount() {
        return agendaList.size();
    }

    public void atualizaListaRecyclerView(List<Agenda> novaLista){
        this.agendaList.clear();
        this.agendaList = novaLista;
        notifyDataSetChanged();
    }

    public class ViewHoleder extends RecyclerView.ViewHolder {
        private TextView nomeContato;
        private TextView telefone;

        public ViewHoleder(@NonNull View itemView) {
            super(itemView);

            nomeContato = itemView.findViewById(R.id.txtNome);
            telefone = itemView.findViewById(R.id.txtTelefone);
        }

        public void bind(Agenda agenda){
            nomeContato.setText(agenda.getNomeContato());
            telefone.setText(agenda.getTelefone());
        }
    }
}
