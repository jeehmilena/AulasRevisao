package com.jessica.digitalhouse.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.jessica.digitalhouse.R;
import com.jessica.digitalhouse.adapters.RecyclerViewAdapter;
import com.jessica.digitalhouse.interfaces.RecyclerViewClick;
import com.jessica.digitalhouse.model.Result;
import com.jessica.digitalhouse.viewmodel.ResultViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClick {

    private RecyclerView recyclerView;
    private List<Result> results = new ArrayList<>();
    private ResultViewModel viewModel;
    private int pag = 1;
    private int totalpag = 10;
    private ProgressBar progressBar;
    private RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        //recyclerView em modo horizontal
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //recyclerView em modo vertical
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //recyclerView em modo Grid
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        //setando a nova lista para o adapter do recyclerView
        viewModel.getMovies("e1a9eef62eef24833db25f0491f893c7", "PT-BR");
        viewModel.resultsLiveData.observe(this, (List<Result> results) -> {
            adapter.setResult(results);
        });

        //mudando a visibilidade da barra de progresso de acordo com o retorno do isLoading
        viewModel.isLoading.observe(this, (Boolean loading) -> {
            if (loading){
                progressBar.setVisibility(View.VISIBLE);
            }else{
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    // Inicializa as Views
    private void initViews() {
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progress_bar);
        adapter = new RecyclerViewAdapter(results, this);
        viewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
    }

    @Override
    public void clickListenner(Result result) {
        long id = result.getId();
        Toast.makeText(this, "ID "+result.getId(), Toast.LENGTH_SHORT).show();
    }
}
