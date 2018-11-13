package com.jessica.digitalhouse.agendaroom.view;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jessica.digitalhouse.agendaroom.R;
import com.jessica.digitalhouse.agendaroom.adapters.RecyclerViewAdapter;
import com.jessica.digitalhouse.agendaroom.data.AgendaBancoDados;
import com.jessica.digitalhouse.agendaroom.data.AgendaDAO;
import com.jessica.digitalhouse.agendaroom.interfaces.RecyclerViewOnClick;
import com.jessica.digitalhouse.agendaroom.model.Agenda;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewOnClick {

    private TextInputLayout contato;
    private TextInputLayout telefone;
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnDelete;
    private RecyclerView recyclerView;
    private List<Agenda> listaAgenda = new ArrayList<>();
    private AgendaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contato = findViewById(R.id.textInputLayoutNome);
        telefone = findViewById(R.id.textInputLayoutTelefone);
        btnAdd = findViewById(R.id.floatingActionButtonAdd);
        btnDelete = findViewById(R.id.floatingActionButtonDelete);
        recyclerView = findViewById(R.id.recyclerViewAgenda);

        //Incializando o bando de dados
        AgendaBancoDados bancoDados = AgendaBancoDados.getDatabase(this);
        dao = bancoDados.agendaDAO();

        RecyclerViewAdapter adapter = new  RecyclerViewAdapter(listaAgenda, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        retornaTodosOsDadosDoBanco(adapter);

        //Botão de Adicionar
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nomeContato = contato.getEditText().getText().toString();
                final String telefoneContato = telefone.getEditText().getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Agenda agenda = dao.getNomeContato(nomeContato);

                        if (agenda != null){
                            agenda.setNomeContato(nomeContato);
                            agenda.setTelefone(telefoneContato);
                            dao.atualizarAgenda(agenda);
                            System.out.println("Atualizado");
                        }else{

                            agenda = new Agenda();
                            agenda.setNomeContato(nomeContato);
                            agenda.setTelefone(telefoneContato);
                            dao.inserirAgenda(agenda);
                        }

                    }
                }).start();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nomeContato = contato.getEditText().getText().toString();
                final String telefoneContato = telefone.getEditText().getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Agenda agenda = dao.getNomeContato(nomeContato);
                        if (agenda != null){
                            dao.deletarContato(agenda);
                        }else {
                            System.out.println("Não encontrado!");
                        }
                    }
                }).start();
            }
        });

    }

    private void retornaTodosOsDadosDoBanco(final RecyclerViewAdapter adapter) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.todosContatos().observe(MainActivity.this, new Observer<List<Agenda>>() {
                    @Override
                    public void onChanged(@Nullable List<Agenda> agenda) {
                            adapter.atualizaListaRecyclerView(agenda);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(Agenda agenda) {
        contato.getEditText().setText(agenda.getNomeContato());
        telefone.getEditText().setText(agenda.getTelefone());
    }
}
